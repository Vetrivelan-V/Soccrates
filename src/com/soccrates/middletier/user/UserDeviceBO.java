package com.soccrates.middletier.user;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEntity.
 *
 * @author bvbi-infotech
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDeviceBO {

	private long userDeviceId;
	private int userDeviceType;
	private String deviceKey;
	private boolean active;

	public long getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(long userDeviceId) {
		this.userDeviceId = userDeviceId;
	}

	public int getUserDeviceType() {
		return userDeviceType;
	}

	public void setUserDeviceType(int userDeviceType) {
		this.userDeviceType = userDeviceType;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	 
 

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
