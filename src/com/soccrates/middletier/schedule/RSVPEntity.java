package com.soccrates.middletier.schedule;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.soccrates.middletier.constant.ApplicationTableConstants;
import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "rsvp")
public class RSVPEntity {

	
	private long rsvpId; 
	private int rsvpType; //1 rsvp //2 attendance
	private int attendance;
	private UserEntity playerId;
	private Date createdDate= new Date();
	@Column(length = ApplicationTableConstants.EXCEPTION_MESSAGE)
	private String comments;
	private ScheduleEntity scheduleEntity;
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

//	private List<ReminderEntity> reminderEntity;
//	private List<AttendanceEntity> attendanceEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getRsvpId() {
		return rsvpId;
	}

	public void setRsvpId(long rsvpId) {
		this.rsvpId = rsvpId;
	}

	 
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "playerId")
	public UserEntity getPlayerId() {
		return playerId;
	}
	public void setPlayerId(UserEntity playerId) {
		this.playerId = playerId;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "scheduleId_rsvp")
	public ScheduleEntity getScheduleEntity() {
		return scheduleEntity;
	}

	public void setScheduleEntity(ScheduleEntity scheduleEntity) {
		this.scheduleEntity = scheduleEntity;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
 

 

}
