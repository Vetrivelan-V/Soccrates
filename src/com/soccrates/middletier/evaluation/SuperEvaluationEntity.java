package com.soccrates.middletier.evaluation;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.template.TemplateEntity;
import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "super_evaluation")
public class SuperEvaluationEntity {

	private long super_evaluationId;
	private TemplateEntity templateId;
	private boolean published;
	private UserEntity player;
	private UserEntity coach;
	private TeamEntity teamEntity;


	List<EvaluationEntity> evaluationEntities;
	private Date creationDate,publishedDate;

	private String comments;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getSuper_evaluationId() {
		return super_evaluationId;
	}

	public void setSuper_evaluationId(long super_evaluationId) {
		this.super_evaluationId = super_evaluationId;
	}

	@OneToMany(fetch = FetchType.EAGER, targetEntity = EvaluationEntity.class, mappedBy = "superEvaluationEntity")
	public List<EvaluationEntity> getEvaluationEntities() {
		return evaluationEntities;
	}

	public void setEvaluationEntities(List<EvaluationEntity> evaluationEntities) {
		this.evaluationEntities = evaluationEntities;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "playerId",referencedColumnName="userId")
	public UserEntity getPlayer() {
		return player;
	}
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "templateId",referencedColumnName="templateId")
	public TemplateEntity getTemplateId() {
		return templateId;
	}

	public void setTemplateId(TemplateEntity templateId) {
		this.templateId = templateId;
	}

	public void setPlayer(UserEntity player) {
		this.player = player;
	}
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "coachId",referencedColumnName="userId")
	public UserEntity getCoach() {
		return coach;
	}

	public void setCoach(UserEntity coach) {
		this.coach = coach;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isPublished() {
		return published;
	}

	 

	public void setPublished(boolean published) {
		this.published = published;
	}
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "teamEntity",referencedColumnName="teamId")
	public TeamEntity getTeamEntity() {
		return teamEntity;
	}

	public void setTeamEntity(TeamEntity teamEntity) {
		this.teamEntity = teamEntity;
	}


}
