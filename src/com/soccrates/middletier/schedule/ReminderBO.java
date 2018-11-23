package com.soccrates.middletier.schedule;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReminderBO extends SoccratesObject {
	private long reminderId;
	private int repeatType;
	private int alertType;
	private int secondAlert;
	private String scheduledDate, startDate, endDate, repeatDate, endRepeatDate;
	private long scheduleId;
	private long userId;

	public long getReminderId() {
		return reminderId;
	}

	public void setReminderId(long reminderId) {
		this.reminderId = reminderId;
	}

	public int getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(int repeatType) {
		this.repeatType = repeatType;
	}

	public int getAlertType() {
		return alertType;
	}

	public void setAlertType(int alertType) {
		this.alertType = alertType;
	}

	public int getSecondAlert() {
		return secondAlert;
	}

	public void setSecondAlert(int secondAlert) {
		this.secondAlert = secondAlert;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRepeatDate() {
		return repeatDate;
	}

	public void setRepeatDate(String repeatDate) {
		this.repeatDate = repeatDate;
	}

	public String getEndRepeatDate() {
		return endRepeatDate;
	}

	public void setEndRepeatDate(String endRepeatDate) {
		this.endRepeatDate = endRepeatDate;
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

}
