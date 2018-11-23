package com.soccrates.middletier.evaluation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperEvaluationListBO extends SoccratesObject {

	private List<SuperEvaluationBO> superEvaluationBOs = new ArrayList<>();
	private long coachId;
	private long playerId;
	private long templateId;
	private long categoryId;
	private long teamId;
	private boolean published;
	private int publishedSearch=0;

	public List<SuperEvaluationBO> getSuperEvaluationBOs() {
		return superEvaluationBOs;
	}

	public void setSuperEvaluationBOs(List<SuperEvaluationBO> superEvaluationBOs) {
		this.superEvaluationBOs = superEvaluationBOs;
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

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public int getPublishedSearch() {
		return publishedSearch;
	}

	public void setPublishedSearch(int publishedSearch) {
		this.publishedSearch = publishedSearch;
	}

}
