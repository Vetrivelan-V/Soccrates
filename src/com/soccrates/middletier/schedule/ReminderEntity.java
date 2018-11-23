package com.soccrates.middletier.schedule;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.user.UserEntity;
@Entity
@Table(name = "reminder")
public class ReminderEntity {
	private long reminderId;
	private int repeatType;
	private int alertType;
	private int secondAlert;
	private Date scheduledDate,startDate,endDate,repeatDate, endRepeatDate;
	private ScheduleEntity scheduleEnity;
	private UserEntity userId;
	
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getReminderId() {
		return reminderId;
	}
	public void setReminderId(long reminderId) {
		this.reminderId = reminderId;
	}
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public UserEntity getUserId() {
		return userId;
	}
	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scheduleId_reminder")
	public ScheduleEntity getScheduleEnity() {
		return scheduleEnity;
	}
	public void setScheduleEnity(ScheduleEntity scheduleEnity) {
		this.scheduleEnity = scheduleEnity;
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
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getRepeatDate() {
		return repeatDate;
	}
	public void setRepeatDate(Date repeatDate) {
		this.repeatDate = repeatDate;
	}
	public Date getEndRepeatDate() {
		return endRepeatDate;
	}
	public void setEndRepeatDate(Date endRepeatDate) {
		this.endRepeatDate = endRepeatDate;
	}
	
	
}
