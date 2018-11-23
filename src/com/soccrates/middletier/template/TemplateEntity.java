package com.soccrates.middletier.template;

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

import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "template",uniqueConstraints=@UniqueConstraint(columnNames = { "templateName","userId" }))
public class TemplateEntity {

	private long templateId;
	private UserEntity coach;
	private String templateName;
	
//	private List<CategoryEntity> criteria;
//		 
//	  
//	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity=CategoryEntity.class,mappedBy="categoryId")
//	@JoinColumn(name="categoryId")
//	public List<CategoryEntity> getCriteria() {
//		return criteria;
//	}
//
//	public void setCriteria(List<CategoryEntity> criteria) {
//		this.criteria = criteria;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	public UserEntity getCoach() {
		return coach;
	}

	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setCoach(UserEntity coach) {
		this.coach = coach;
	}

	 
	 
	 
}
