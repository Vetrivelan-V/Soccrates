package com.soccrates.middletier.evaluation;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationListBO extends SoccratesObject {

	ArrayList<EvaluationBO> evaluationBOs = new ArrayList<>();
	private long coachId;
	private long playerId;
	private long templateId;
	private long categoryId;

	public ArrayList<EvaluationBO> getEvaluationBOs() {
		return evaluationBOs;
	}

	public void setEvaluationBOs(ArrayList<EvaluationBO> evaluationBOs) {
		this.evaluationBOs = evaluationBOs;
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

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

}
