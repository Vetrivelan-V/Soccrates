package com.soccrates.middletier.team;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.address.AddressBO;
import com.soccrates.middletier.user.UserBO;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamBO extends SoccratesObject {
	private long teamId;
	private UserBO coach;
	private long coachId;
	private String teamName;
	private String ageGroup;
	private String gender;
	private String imagePath;
	private String imageUrl;
	private AddressBO teamAddress;

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public UserBO getCoach() {
		return coach;
	}

	public void setCoach(UserBO coach) {
		this.coach = coach;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public AddressBO getTeamAddress() {
		return teamAddress;
	}

	public void setTeamAddress(AddressBO teamAddress) {
		this.teamAddress = teamAddress;
	}

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}

	@Override
	public String toString() {
		return "TeamBO [teamId=" + teamId + ", coach=" + coach + ", teamName=" + teamName + ", ageGroup=" + ageGroup
				+ ", gender=" + gender + ", imagePath=" + imagePath + ", imageUrl=" + imageUrl + ", teamAddress="
				+ teamAddress + "]";
	}

	public void copy(TeamEntity teamEntity, String path) {
		// TODO Auto-generated method stub
		this.setAgeGroup(teamEntity.getAgeGroup());
//		this.getCoach().copy(coach, path);
		this.setGender(teamEntity.getGender());
//		if (teamEntity.getImageUrl() == null || "null".equalsIgnoreCase(teamEntity.getImageUrl())
//				|| teamEntity.getImageUrl().trim().length() == 0)
//			this.setImageUrl(ApplicationConstants.TEAMDEFUALTIMAGE);
//		else
		this.setImageUrl("http://34.218.114.30/team/" + teamEntity.getTeamId() + ".png");
		this.setTeamId(teamEntity.getTeamId());
//		this.getTeamAddress().copy(teamEntity.getTeamAddress());
		this.setTeamName(teamEntity.getTeamName());
	}

}
