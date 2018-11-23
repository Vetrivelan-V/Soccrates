package com.soccrates.middletier.team;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamPlayerBO extends SoccratesObject {
	private long teamPlayerId;
	private long playerId;
	private long coachId;
	private long teamId;
	private boolean activated;
	private boolean acceptedInvitation;
	private TeamBO teamBO;
	
	private String emailId,firstName,lastName;
	public long getTeamPlayerId() {
		return teamPlayerId;
	}
	public void setTeamPlayerId(long teamPlayerId) {
		this.teamPlayerId = teamPlayerId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public boolean isAcceptedInvitation() {
		return acceptedInvitation;
	}
	public void setAcceptedInvitation(boolean acceptedInvitation) {
		this.acceptedInvitation = acceptedInvitation;
	}
	public TeamBO getTeamBO() {
		return teamBO;
	}
	public void setTeamBO(TeamBO teamBO) {
		this.teamBO = teamBO;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "TeamPlayerBO [teamPlayerId=" + teamPlayerId + ", playerId=" + playerId + ", coachId=" + coachId
				+ ", teamId=" + teamId + ", activated=" + activated + ", acceptedInvitation=" + acceptedInvitation
				+ ", teamBO=" + teamBO + ", emailId=" + emailId + "]";
	}
	
	
}
