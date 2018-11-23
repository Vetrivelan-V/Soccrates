package com.soccrates.middletier.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleBO extends SoccratesObject {

	private long scheduleId, teamId;
	private int scheduleType;
	private String titleName;
	private String placeName;
	private long coachId;
	private String createdDate;
	private long score;
	private int uniformColor;
	private String comments;

	private List<RsvpBO> rsvpBOs = new ArrayList<>();

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

	private List<ReminderBO> reminderBOs;

	public long getScheduleId() {
		return scheduleId;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
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

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<ReminderBO> getReminderBOs() {
		return reminderBOs;
	}

	public void setReminderBOs(List<ReminderBO> reminderBOs) {
		this.reminderBOs = reminderBOs;
	}

	public List<RsvpBO> getRsvpBOs() {
		return rsvpBOs;
	}

	public void setRsvpBOs(List<RsvpBO> rsvpBOs) {
		this.rsvpBOs = rsvpBOs;
	}

	@Override
	public String toString() {
		return "ScheduleBO [scheduleId=" + scheduleId + ", teamId=" + teamId + ", scheduleType=" + scheduleType
				+ ", titleName=" + titleName + ", placeName=" + placeName + ", coachId=" + coachId + ", createdDate="
				+ createdDate + ", score=" + score + ", uniformColor=" + uniformColor + ", comments=" + comments
				+ ", rsvpBOs=" + rsvpBOs + ", reminderBOs=" + reminderBOs + "]";
	}

}
