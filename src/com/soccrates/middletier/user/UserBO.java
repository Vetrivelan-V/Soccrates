package com.soccrates.middletier.user;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.soccrates.middletier.constant.ApplicationConstants;
// TODO: Auto-generated Javadoc
import com.soccrates.middletier.util.SoccratesObject;

/**
 * The Class UserBO.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBO extends SoccratesObject {

	private long userId, companyId = 1;
	private int userType;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String profilePic, emailId;
	private boolean coach;
	private long coachId;
	private boolean active;
	private String phoneNumber, about_Desc;
	private String nickName, loginId, password;
	private String creationDate, lastLoginTime;
	private String dateOfBirth;
	private boolean emailIdValid;
	private boolean phoneNumberValid;
	private String favTeam;
	private String playerNumber, position,favPlayer;
	private UserDeviceBO userDeviceBO = new UserDeviceBO();

	public long getUserId() {
		return userId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isCoach() {
		return coach;
	}

	public void setCoach(boolean coach) {
		this.coach = coach;
	}

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAbout_Desc() {
		return about_Desc;
	}

	public void setAbout_Desc(String about_Desc) {
		this.about_Desc = about_Desc;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isEmailIdValid() {
		return emailIdValid;
	}

	public void setEmailIdValid(boolean emailIdValid) {
		this.emailIdValid = emailIdValid;
	}

	public boolean isPhoneNumberValid() {
		return phoneNumberValid;
	}

	public void setPhoneNumberValid(boolean phoneNumberValid) {
		this.phoneNumberValid = phoneNumberValid;
	}

	public String getFavTeam() {
		return favTeam;
	}

	public void setFavTeam(String favTeam) {
		this.favTeam = favTeam;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFavPlayer() {
		return favPlayer;
	}

	public void setFavPlayer(String favPlayer) {
		this.favPlayer = favPlayer;
	}

	public void copy(UserEntity userEntity, String profilePath) {
		// TODO Auto-generated method stub
		if (userEntity != null) {
			this.setUserId(userEntity.getUserId());
			this.setUserType(userEntity.getUserType());
			this.setFirstName(userEntity.getFirstName());
			this.setLastName(userEntity.getLastName());
			this.setMiddleInitial(userEntity.getMiddleInitial());
//			private String playerNumber, position,favPlayers;
			this.playerNumber=userEntity.getPlayerNumber();
			this.setPosition(userEntity.getPosition());
			this.setFavPlayer(userEntity.getFavPlayer());
//			if (userEntity.getProfilePic() == null || "null".equalsIgnoreCase(userEntity.getProfilePic())
//					|| userEntity.getProfilePic().trim().length() > 0)
//				this.setProfilePic(ApplicationConstants.USERDEFAULTIMAGE);
//			else
				this.setProfilePic("http://34.218.114.30/user/"+userEntity.getUserId()+".png");
			this.setEmailId(userEntity.getEmailId());
			this.setCoach((userEntity.getCreatedBy() != null) ? false : true);
			this.setCoachId((userEntity.getCreatedBy() != null) ? userEntity.getCreatedBy().getUserId() : 0);
			this.setActive(userEntity.isActive());
			this.setPhoneNumber(userEntity.getPhoneNumber());
			this.setAbout_Desc(userEntity.getAbout_Desc());
			this.setNickName(userEntity.getNickName());
			this.setLoginId(userEntity.getLoginId());

			try {
				if (userEntity.getCreationDate() != null)
					this.setCreationDate(ApplicationConstants.US_DATE_FORMAT.format(userEntity.getCreationDate()));

				if (userEntity.getLastLoginTime() != null)
					this.setLastLoginTime(ApplicationConstants.DATE_WITH_TIME.format(userEntity.getLastLoginTime()));

				if (userEntity.getDateOfBirth() != null)
					this.setDateOfBirth(ApplicationConstants.US_DATE_FORMAT.format(userEntity.getDateOfBirth()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.setEmailIdValid(userEntity.isEmailIdValid());
			this.setPhoneNumberValid(userEntity.isPhoneNumberValid());
			this.setFavTeam(userEntity.getFavTeam());
		}
		
	}

	public UserDeviceBO getUserDeviceBO() {
		return userDeviceBO;
	}

	public void setUserDeviceBO(UserDeviceBO userDeviceBO) {
		this.userDeviceBO = userDeviceBO;
	}

	@Override
	public String toString() {
		return "UserBO [userId=" + userId + ", companyId=" + companyId + ", userType=" + userType + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleInitial=" + middleInitial + ", profilePic="
				+ profilePic + ", emailId=" + emailId + ", coach=" + coach + ", coachId=" + coachId + ", active="
				+ active + ", phoneNumber=" + phoneNumber + ", about_Desc=" + about_Desc + ", nickName=" + nickName
				+ ", loginId=" + loginId + ", password=" + password + ", creationDate=" + creationDate
				+ ", lastLoginTime=" + lastLoginTime + ", dateOfBirth=" + dateOfBirth + ", emailIdValid=" + emailIdValid
				+ ", phoneNumberValid=" + phoneNumberValid + ", favTeam=" + favTeam + "]";
	}

}
