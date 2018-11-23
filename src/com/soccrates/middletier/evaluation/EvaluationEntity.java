package com.soccrates.middletier.evaluation;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.template.CategoryEntity;

@Entity
@Table(name = "evaluation")
public class EvaluationEntity {

	private CategoryEntity categoryEntity;
	private long eValue;
//	private boolean published;
	private long evaluationId;

	private SuperEvaluationEntity superEvaluationEntity;
	 private Date createdDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(long evaluationId) {
		this.evaluationId = evaluationId;
	}

	 

	@ManyToOne
	@JoinColumn(name = "super_evaluationId")
	public SuperEvaluationEntity getSuperEvaluationEntity() {
		return superEvaluationEntity;
	}

	public void setSuperEvaluationEntity(SuperEvaluationEntity superEvaluationEntity) {
		this.superEvaluationEntity = superEvaluationEntity;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public long geteValue() {
		return eValue;
	}

	public void seteValue(long eValue) {
		this.eValue = eValue;
	}

//	public boolean isPublished() {
//		return published;
//	}
//
//	public void setPublished(boolean published) {
//		this.published = published;
//	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

 

}
