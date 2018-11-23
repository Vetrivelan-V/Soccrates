package com.soccrates.middletier.team;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "teamplayer", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "teamId"})})
public class TeamPlayerEntity {
	private long teamPlayerId;
	private UserEntity player;
	private boolean activated;
	private boolean acceptedInvitation;
	private TeamEntity team;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getTeamPlayerId() {
		return teamPlayerId;
	}
	public void setTeamPlayerId(long teamPlayerId) {
		this.teamPlayerId = teamPlayerId;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public UserEntity getPlayer() {
		return player;
	}
	public void setPlayer(UserEntity player) {
		this.player = player;
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
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="teamId")
	public TeamEntity getTeam() {
		return team;
	}
	public void setTeam(TeamEntity team) {
		this.team = team;
	}
	
	

}
