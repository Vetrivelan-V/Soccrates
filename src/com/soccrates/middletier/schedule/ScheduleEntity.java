package com.soccrates.middletier.schedule;

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

import com.soccrates.middletier.constant.ApplicationTableConstants;
import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

	private TeamEntity teamEntity;
	private long scheduleId;
	private int scheduleType;
	private String titleName;
	private String placeName;
	private UserEntity coach;
	private Date createdDate;
	private long score;
	@Column(length = ApplicationTableConstants.EXCEPTION_MESSAGE)
	private String comments;
	private int uniformColor;
	private List<ReminderEntity> reminderEntity; 
	private List<RSVPEntity> rsvpEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public UserEntity getCoach() {
		return coach;
	}

	public void setCoach(UserEntity coach) {
		this.coach = coach;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "teamId")
	public TeamEntity getTeamEntity() {
		return teamEntity;
	}

	public void setTeamEntity(TeamEntity teamEntity) {
		this.teamEntity = teamEntity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(int scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity=ReminderEntity.class,mappedBy="scheduleEnity")
	@JoinColumn(name="reminderId")
	public List<ReminderEntity> getReminderEntity() {
		return reminderEntity;
	}
	 
	public void setReminderEntity(List<ReminderEntity> reminderEntity) {
		this.reminderEntity = reminderEntity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getUniformColor() {
		return uniformColor;
	}

	public void setUniformColor(int uniformColor) {
		this.uniformColor = uniformColor;
	}
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity=RSVPEntity.class,mappedBy="scheduleEntity")
	@JoinColumn(name="rsvpId")
	public List<RSVPEntity> getRsvpEntity() {
		return rsvpEntity;
	}

	public void setRsvpEntity(List<RSVPEntity> rsvpEntity) {
		this.rsvpEntity = rsvpEntity;
	}
	
	
//	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity=AttendanceEntity.class,mappedBy="scheduleEnity")
//	@JoinColumn(name="attendanceId")
//	
//	public List<AttendanceEntity> getAttendanceEntity() {
//		return attendanceEntity;
//	}
//
//	public void setAttendanceEntity(List<AttendanceEntity> attendanceEntity) {
//		this.attendanceEntity = attendanceEntity;
//	}
	

}
