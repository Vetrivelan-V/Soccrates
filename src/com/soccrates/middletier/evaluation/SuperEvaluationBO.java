package com.soccrates.middletier.evaluation;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperEvaluationBO extends SoccratesObject {

	private boolean published;
	private long super_evaluationId, templateId,teamId;

	private long playerId, coachId;
	private String createdDate, publishedDate;
	private String comments;
	private List<EvaluationBO> evaluationBOs;

	public long getSuper_evaluationId() {
		return super_evaluationId;
	}

	public void setSuper_evaluationId(long super_evaluationId) {
		this.super_evaluationId = super_evaluationId;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

 

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public List<EvaluationBO> getEvaluationBOs() {
		return evaluationBOs;
	}

	public void setEvaluationBOs(List<EvaluationBO> evaluationBOs) {
		this.evaluationBOs = evaluationBOs;
	}

	public void copy(SuperEvaluationEntity evaluationEntity) {
		// TODO Auto-generated method stub
		this.published = evaluationEntity.isPublished();
		this.super_evaluationId = evaluationEntity.getSuper_evaluationId();
		this.playerId = evaluationEntity.getPlayer().getUserId();
		this.coachId = evaluationEntity.getCoach().getUserId();
		this.teamId=evaluationEntity.getTeamEntity().getTeamId();
		this.createdDate = (evaluationEntity.getCreationDate() != null)
				? ApplicationConstants.DATE_WITH_TIME.format(evaluationEntity.getCreationDate())
				: null;
		this.publishedDate = (evaluationEntity.getPublishedDate() != null)
				? ApplicationConstants.DATE_WITH_TIME.format(evaluationEntity.getPublishedDate())
				: null;
		this.comments = evaluationEntity.getComments();
		this.templateId=evaluationEntity.getTemplateId().getTemplateId();
	}

}
