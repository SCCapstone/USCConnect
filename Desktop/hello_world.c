/*
 * David Bare and Joseph Ammer
 * CSCE313 Lab #6
 *
 */

#include <stdio.h>
#include <unistd.h>
#include "system.h"
#include "altera_avalon_pio_regs.h"
#include "alt_types.h"
#include <time.h>
#include <stdlib.h>
#include <math.h>
#include <altera_avalon_mailbox.h>
#include <altera_up_avalon_video_pixel_buffer_dma.h>
#include <altera_avalon_performance_counter.h>

int numCPUS = 4;

long local_target[2];
volatile long *global_target = local_target;

void barrier(alt_u8 barrier_num) {
	alt_u32 msg;
	alt_mailbox_dev*mb[numCPUS];
	char mb_name[80];
	int cpu = __builtin_rdctl(5), i;

	//open all mailboxes
	for (i = 0; i < numCPUS; i++) {
		sprintf(mb_name, "/dev/mailbox_%d", i);
		mb[i] = altera_avalon_mailbox_open(mb_name);
	}
	// post one message to all other mailboxes
	for (i = 0; i < numCPUS; i++) {
		if (i != cpu) {
			if (cpu == 0)
				altera_avalon_mailbox_post(mb[i], (int) local_target);
			else
				altera_avalon_mailbox_post(mb[i], 0);
		}
	}
	// receive one message from all other CPUs
	for (i = 0; i < numCPUS - 1; i++) {

		msg = altera_avalon_mailbox_pend(mb[cpu]);

		if (msg != 0) {
			global_target = (long *)msg;
			//global_target = (float *) msg;
			//global_target = ((float *)msg)+1;
		}
	}
	// close mailboxes
	for (i = 0; i < numCPUS; i++)
		altera_avalon_mailbox_close(mb[i]);
}

int main() {

	int cpuid = __builtin_rdctl(5);

//	PERF_RESET(PERFORMANCE_COUNTER_0_BASE);
//	PERF_START_MEASURING(PERFORMANCE_COUNTER_0_BASE);

	alt_u64 countnum;

	alt_up_pixel_buffer_dma_dev *my_pixel_buffer;

	my_pixel_buffer = alt_up_pixel_buffer_dma_open_dev(
			"/dev/video_pixel_buffer_dma_0");

	long target_X;
	long target_Y;

	long CxMin, CyMin, CxMax, CyMax, Cxpow, Cypow;
	long zoom = 1;
	int zoomSet = 0;


#ifdef MASTER
	volatile alt_u8 pixel_buffer_memory[2359296];

	alt_up_pixel_buffer_dma_change_back_buffer_address(my_pixel_buffer,
			(unsigned int) pixel_buffer_memory);
	alt_up_pixel_buffer_dma_swap_buffers(my_pixel_buffer);
	while (alt_up_pixel_buffer_dma_check_swap_buffers_status(my_pixel_buffer))
		;
	alt_up_pixel_buffer_dma_change_back_buffer_address(my_pixel_buffer,
			(unsigned int) pixel_buffer_memory);
#endif

	while (1) {
		printf("Start: %d\n", cpuid);

		static unsigned char color[3];
		alt_up_pixel_buffer_dma_clear_screen(my_pixel_buffer, 0);

		int iX, iY;
		const int iXmax = 1024;
		const int iYmax = 768;

		long Cx, Cy;
		if (zoom == 1) {
			CxMin = -167772160; // -2.5*2^26
			CxMax = 67108864; // 1*2^26
			CyMin = -67108864; // -1*2^26
			CyMax = 67108864; // 1*2^26

		} else {
			float xpow = (1.0 / powf(1.5,zoom));
			float ypow = (0.75 / powf(1.5,zoom));
			Cxpow = (long) (xpow*(float) 67108864);
			Cypow = (long) (ypow*(float) 67108864);
			CxMin = target_X - Cxpow;
			CxMax = target_X + Cxpow;
			CyMin = target_Y - Cypow;
			CyMax = target_Y + Cypow;
		}

		long PixelWidth = (CxMax - CxMin) / iXmax;
		long PixelHeight = (CyMax - CyMin) / iYmax;

		long Zx, Zy;
		long long ZxLL, ZyLL;
		int Iteration;
		const int maxIteration = 100;

		long EscapeRadius = 2 << 26;
		long long ER2;
		ER2 = ((long long) EscapeRadius * (long long) EscapeRadius);

		//PERF_BEGIN (PERFORMANCE_COUNTER_0_BASE, 1);

		for (iY = cpuid; iY < iYmax; iY += numCPUS) {
			Cy = CyMin + iY * PixelHeight;
			if ((long) labs(Cy) < PixelHeight / 2)
				Cy = 0 << 26;
			for (iX = 0; iX < iXmax; iX++) {
				Cx = CxMin + iX * PixelWidth;
				Zx = 0 << 26;
				Zy = 0 << 26;
				ZxLL = (long long) Zx * (long long) Zx;
				ZyLL = (long long) Zy * (long long) Zy;

//				PERF_BEGIN(PERFORMANCE_COUNTER_0_BASE, 1);

				for (Iteration = 0; Iteration < maxIteration && ((ZxLL + ZyLL) < ER2); Iteration++) {
					long long tempLL = ((long long) Zx * (long long) Zy);
					Zy = 2 * (tempLL >> 26) + Cy;
					Zx = ((ZxLL - ZyLL) >> 26) + Cx;
					ZxLL = (long long) Zx * (long long) Zx;
					ZyLL = (long long) Zy * (long long) Zy;
				};

//				PERF_END (PERFORMANCE_COUNTER_0_BASE, 1);
//				PERF_STOP_MEASURING(PERFORMANCE_COUNTER_0_BASE);
//				countnum=perf_get_section_time((void*)PERFORMANCE_COUNTER_0_BASE, 1);
//				printf("%lu\n",countnum);

				if (Iteration == maxIteration) {
					color[0] = 0;
					color[1] = 0;
					color[2] = 0;
				} else {
					if (cpuid == 0 && Iteration >= 85 && zoomSet == 0) {
						global_target[0] = Cx;
						global_target[1] = Cy;
						alt_dcache_flush_all();
						zoomSet = 1;
					}
					if (Iteration * 2 < 31)
						color[0] = (Iteration * 2);
					else
						color[0] = 30;
					if (Iteration * 4 < 63)
						color[1] = (Iteration * 4);
					else
						color[1] = 62;
					if (Iteration * 6 < 31)
						color[2] = (Iteration * 6);
					else
						color[2] = 30;
				}
				int RGB = ((color[0] << 11) & 0xF800) | ((color[1] << 5)
						& 0x07E0) | (color[2] & 0x001F);

				alt_up_pixel_buffer_dma_draw(my_pixel_buffer, RGB, iX, iY);
			}
			barrier(0);
		}
		barrier(0);
		target_X = global_target[0];
		target_Y = global_target[1];
		zoom++;

		//PERF_END (PERFORMANCE_COUNTER_0_BASE, 1);
		//PERF_STOP_MEASURING(PERFORMANCE_COUNTER_0_BASE);
		//countnum=perf_get_section_time((void*)PERFORMANCE_COUNTER_0_BASE, 1);
		//printf("%lu\n",countnum);
		//PERF_RESET(PERFORMANCE_COUNTER_0_BASE);
		//PERF_START_MEASURING(PERFORMANCE_COUNTER_0_BASE);

	}
	return 0;
}
