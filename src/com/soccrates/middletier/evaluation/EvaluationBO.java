package com.soccrates.middletier.evaluation;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationBO extends SoccratesObject {

	private long categoryId;
	private long eValue;
//	private boolean published;
	private long evaluationId, templateId;

	private long playerId, coachId;
	private Date createdDate;

	private long super_evaluationId;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long geteValue() {
		return eValue;
	}

	public void seteValue(long eValue) {
		this.eValue = eValue;
	}

	 

	public long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(long evaluationId) {
		this.evaluationId = evaluationId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getSuper_evaluationId() {
		return super_evaluationId;
	}

	public void setSuper_evaluationId(long super_evaluationId) {
		this.super_evaluationId = super_evaluationId;
	}

	 
	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public void copy(EvaluationEntity evaluationEntity) {
		// TODO Auto-generated method stub
		this.categoryId = evaluationEntity.getCategoryEntity().getCategoryId();
		this.eValue = evaluationEntity.geteValue();
		this.super_evaluationId = evaluationEntity.getSuperEvaluationEntity().getSuper_evaluationId();
//		this.published = evaluationEntity.isPublished();
		this.evaluationId = evaluationEntity.getEvaluationId();
		this.createdDate = evaluationEntity.getCreatedDate();
//		this.publishedDate = evaluationEntity.getPublishedDate();
//		this.comments = evaluationEntity.getComments();
	}

}
