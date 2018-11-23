package com.soccrates.middletier.util;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.soccrates.middlertier.mail.MailSettingEntity;

@Entity
@Table(name = "settings")
public class SettingsEntity {
	private long settingsId;
	private String profileImagePath,urlPath;
	private String mail_Username,mail_Password, playerRegistrationContent,coachRegistrationContent;
	private MailSettingEntity mailsettings;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(long settingsId) {
		this.settingsId = settingsId;
	}


	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="mailId")
	public MailSettingEntity getMailsettings() {
		return mailsettings;
	}

	public void setMailsettings(MailSettingEntity mailsettings) {
		this.mailsettings = mailsettings;
	}

	public String getMail_Username() {
		return mail_Username;
	}

	public void setMail_Username(String mail_Username) {
		this.mail_Username = mail_Username;
	}

	public String getMail_Password() {
		return mail_Password;
	}

	public void setMail_Password(String mail_Password) {
		this.mail_Password = mail_Password;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getPlayerRegistrationContent() {
		return playerRegistrationContent;
	}

	public void setPlayerRegistrationContent(String playerRegistrationContent) {
		this.playerRegistrationContent = playerRegistrationContent;
	}

	public String getCoachRegistrationContent() {
		return coachRegistrationContent;
	}

	public void setCoachRegistrationContent(String coachRegistrationContent) {
		this.coachRegistrationContent = coachRegistrationContent;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

}
