import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConvert {
	public String opportID;
	public String Title;
	public String ExternalLink;
	public String Description;
	public String ExpirationDate;
	public String OpportunityType;
	public String OpportunityTypeOther;
	public String TimeFrame;
	public String TimeFrameOther;
	public String TargetGroup;
	public String TargetGroupOther;
	public String CreativeComponent;
	public String CreativeComponentOther;
	public String StoryTitle;
	public String StoryExternalLink;
	public String StoryDescription;
	public String StoryPhoto;
	public String SponsorCampus;
	public String SponsorProgram;
	public String SponsorCollege;
	public String SponsorOther;
	public String ParticipationInstructions;
	public String Email;
	public String Phone;
	public String PhoneExt;
	public String Name;
	public String SubmittedEmail;
	public String Status;
	public String AutoArchiveDate;
	public String UserAdded;
	public String User2Access;
	public String DateAdded;
	public String DateDeleted;
	public String DateUpdated;

	public static void main(String[] args) {

		int counter = 0;

		String opportID = "";
		String Title = "";
		String ExternalLink = "";
		String Description = "";
		String ExpirationDate = "";
		String OpportunityType = "";
		String OpportunityTypeOther = "";
		String TimeFrame = "";
		String TimeFrameOther = "";
		String TargetGroup = "";
		String TargetGroupOther = "";
		String CreativeComponent = "";
		String CreativeComponentOther = "";
		String StoryTitle = "";
		String StoryExternalLink = "";
		String StoryDescription = "";
		String StoryPhoto = "";
		String SponsorCampus = "";
		String SponsorProgram = "";
		String SponsorCollege = "";
		String SponsorOther = "";
		String ParticipationInstructions = "";
		String Email = "";
		String Phone = "";
		String PhoneExt = "";
		String Name = "";
		String SubmittedEmail = "";
		String Status = "";
		String AutoArchiveDate = "";
		String UserAdded = "";
		String User2Access = "";
		String DateAdded = "";
		String DateDeleted = "";
		String DateUpdated = "";

		try {
			File file = new File(
					"/acct/s1/ammer/Desktop/uscconnect_opportunities_1414761945.csv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null && counter < 3) {
				if (counter == 0) {
					counter++;
				} else {

					stringBuffer.append(line);
					stringBuffer.append("\n");
					String delims = "[,]";
					String[] ColumnData = line.split(delims);
					// System.out.println(ColumnData[0]);

					opportID = ColumnData[0];
					opportID = opportID.substring(1, opportID.length() - 1);
					// System.out.println(opportID);

					Title = ColumnData[1];
					Title = Title.substring(1, Title.length() - 1);
					ExternalLink = ColumnData[2];
					ExternalLink = ExternalLink.substring(1,
							ExternalLink.length() - 1);
					Description = ColumnData[3];
					Description = Description.substring(1,
							Description.length() - 1);
					ExpirationDate = ColumnData[4];
					ExpirationDate = ExpirationDate.substring(1,
							ExpirationDate.length() - 1);
					OpportunityType = ColumnData[5];
					OpportunityType = OpportunityType.substring(1,
							OpportunityType.length() - 1);
					OpportunityTypeOther = ColumnData[6];
					OpportunityTypeOther = OpportunityTypeOther.substring(1,
							OpportunityTypeOther.length() - 1);
					TimeFrame = ColumnData[7];
					TimeFrame = TimeFrame.substring(1, TimeFrame.length() - 1);
					TimeFrameOther = ColumnData[8];
					TimeFrameOther = TimeFrameOther.substring(1,
							TimeFrameOther.length() - 1);
					TargetGroup = ColumnData[9];
					TargetGroup = TargetGroup.substring(1,
							TargetGroup.length() - 1);
					TargetGroupOther = ColumnData[10];
					TargetGroupOther = TargetGroupOther.substring(1,
							TargetGroupOther.length() - 1);
					CreativeComponent = ColumnData[11];
					CreativeComponent = CreativeComponent.substring(1,
							CreativeComponent.length() - 1);
					CreativeComponentOther = ColumnData[12];
					CreativeComponentOther = CreativeComponentOther.substring(
							1, CreativeComponentOther.length() - 1);
					StoryTitle = ColumnData[13];
					StoryTitle = StoryTitle.substring(1,
							StoryTitle.length() - 1);
					StoryExternalLink = ColumnData[14];
					StoryExternalLink = StoryExternalLink.substring(1,
							StoryExternalLink.length() - 1);
					StoryDescription = ColumnData[15];
					StoryDescription = StoryDescription.substring(1,
							StoryDescription.length() - 1);
					StoryPhoto = ColumnData[16];
					StoryPhoto = StoryPhoto.substring(1,
							StoryPhoto.length() - 1);
					SponsorCampus = ColumnData[17];
					SponsorCampus = SponsorCampus.substring(1,
							SponsorCampus.length() - 1);
					SponsorProgram = ColumnData[18];
					SponsorProgram = SponsorProgram.substring(1,
							SponsorProgram.length() - 1);
					SponsorCollege = ColumnData[19];
					SponsorCollege = SponsorCollege.substring(1,
							SponsorCollege.length() - 1);
					SponsorOther = ColumnData[20];
					SponsorOther = SponsorOther.substring(1,
							SponsorOther.length() - 1);
					ParticipationInstructions = ColumnData[21];
					ParticipationInstructions = ParticipationInstructions
							.substring(1,
									ParticipationInstructions.length() - 1);
					Email = ColumnData[22];
					Email = Email.substring(1, Email.length() - 1);
					Phone = ColumnData[23];
					Phone = Phone.substring(1, Phone.length() - 1);
					PhoneExt = ColumnData[24];
					PhoneExt = PhoneExt.substring(1, PhoneExt.length() - 1);
					Name = ColumnData[25];
					Name = Name.substring(1, Name.length() - 1);
					SubmittedEmail = ColumnData[26];
					SubmittedEmail = SubmittedEmail.substring(1,
							SubmittedEmail.length() - 1);
					Status = ColumnData[27];
					Status = Status.substring(1, Status.length() - 1);
					AutoArchiveDate = ColumnData[28];
					AutoArchiveDate = AutoArchiveDate.substring(1,
							AutoArchiveDate.length() - 1);
					UserAdded = ColumnData[29];
					UserAdded = UserAdded.substring(1, UserAdded.length() - 1);
					User2Access = ColumnData[30];
					User2Access = User2Access.substring(1,
							User2Access.length() - 1);
					DateAdded = ColumnData[31];
					DateAdded = DateAdded.substring(1, DateAdded.length() - 1);
					DateDeleted = ColumnData[32];
					DateDeleted = DateDeleted.substring(1,
							DateDeleted.length() - 1);
					DateUpdated = ColumnData[33];
					DateUpdated = DateUpdated.substring(1,
							DateUpdated.length() - 1);

					counter++;
				}
			}
			fileReader.close();
			// System.out.println("Contents of file:");
			// System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}