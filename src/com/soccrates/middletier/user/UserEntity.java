package com.soccrates.middletier.user;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.company.CompanyEntity;
import com.soccrates.middletier.constant.ApplicationTableConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEntity.
 *
 * @author bvbi-infotech
 */
@Entity
@Table(name = "user")

public class UserEntity {
	private long userId;
	private int userType;
	private String password; 
	private String firstName;
	private String lastName; 
	private String middleInitial;
	private String profilePic, emailId;
	private UserEntity createdBy;
	private boolean active;
	private String loginId;
	private String phoneNumber, about_Desc;
	private String nickName;
	private CompanyEntity companyId;
	private Date creationDate, lastLoginTime,updationDate;
	private Date dateOfBirth;
	private boolean emailIdValid;
	private boolean phoneNumberValid;
	private String favTeam;
	private String playerNumber, position,favPlayer;
	private Date sessionActivationEndDate, passwordChangeActivationDate;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "createdBy")
	public UserEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserEntity createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getUserId() {
		return userId;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,targetEntity=UserDeviceEntity.class,mappedBy="ownerId")
	@JoinColumn(name = "userDeviceId")
	private List<UserDeviceEntity>userDeviceEntity;
	 
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(length = ApplicationTableConstants.PASSWORD)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(length = ApplicationTableConstants.PHONE_NUMBER)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId")
	public CompanyEntity getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyEntity companyId) {
		this.companyId = companyId;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	 
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public Date getSessionActivationEndDate() {
		return sessionActivationEndDate;
	}

	public void setSessionActivationEndDate(Date sessionActivationEndDate) {
		this.sessionActivationEndDate = sessionActivationEndDate;
	}

	public Date getPasswordChangeActivationDate() {
		return passwordChangeActivationDate;
	}

	public void setPasswordChangeActivationDate(Date passwordChangeActivationDate) {
		this.passwordChangeActivationDate = passwordChangeActivationDate;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

//	public List<UserDeviceEntity> getUserDeviceEntity() {
//		return userDeviceEntity;
//	}
//
//	public void setUserDeviceEntity(List<UserDeviceEntity> userDeviceEntity) {
//		this.userDeviceEntity = userDeviceEntity;
//	}
	

}
