package com.soccrates.middletier.schedule;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class RsvpBO extends SoccratesObject {
	private long rsvpId;
	private int rsvpType;
	private int attendance;
	private String createdDate;
	private long scheduleId;
	private long userId;
	
	private String name;
	public long getRsvpId() {
		return rsvpId;
	}
	public void setRsvpId(long rsvpId) {
		this.rsvpId = rsvpId;
	}
	public int getRsvpType() {
		return rsvpType;
	}
	public void setRsvpType(int rsvpType) {
		this.rsvpType = rsvpType;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void copy(RSVPEntity rsvpEntity) {
		this.rsvpId=rsvpEntity.getRsvpId();
		this.rsvpType=rsvpEntity.getRsvpType();
		this.attendance=rsvpEntity.getAttendance();
		this.createdDate=ApplicationConstants.DATE_WITH_TIME.format(rsvpEntity.getCreatedDate());
		this.userId=rsvpEntity.getPlayerId().getUserId();
		// TODO Auto-generated method stub
		
	}
 
}
