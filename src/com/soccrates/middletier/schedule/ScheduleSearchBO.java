package com.soccrates.middletier.schedule;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleSearchBO extends SoccratesObject {
	
	private long scheduleId;
	private long coachId; 
	private long playerId,teamId;
	private String scheduledDate;
	private String startDate;
	private String endDate;
	
	private boolean loadRsvp,loadReminder;
	
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
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
	
	public boolean isLoadRsvp() {
		return loadRsvp;
	}
	public void setLoadRsvp(boolean loadRsvp) {
		this.loadRsvp = loadRsvp;
	}
	public boolean isLoadReminder() {
		return loadReminder;
	}
	public void setLoadReminder(boolean loadReminder) {
		this.loadReminder = loadReminder;
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
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	

}
