package com.soccrates.middletier.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEntity.
 *
 * @author bvbi-infotech
 */
@Entity
@Table(name = "user_device")
public class UserDeviceEntity {
	
	private long userDeviceId;
	private int userDeviceType;
	private String deviceKey;
	
	
	
	private UserEntity ownerId;
	private boolean active;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
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

	 
	@ManyToOne
	public UserEntity getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UserEntity ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
