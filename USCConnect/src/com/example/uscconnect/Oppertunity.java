package com.example.search;

public class Oppertunity {
	private String title;
	private String SponsorCollege;
	private String TargetGroup;
	
	public Oppertunity(String title, String SponsorCollege, String TargetGroup) {
		super();
		this.title = title;
		this.SponsorCollege = SponsorCollege;
		this.TargetGroup = TargetGroup;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getSponsorCollege() {
		return SponsorCollege;
	}

	public void setSponsorCollege(String SponsorCollege) {
		this.SponsorCollege = SponsorCollege;
	}

	public String getTargetGroup() {
		return TargetGroup;
	}

	public void setTargetGroup(String TargetGroup) {
		this.TargetGroup = TargetGroup;
	}

}