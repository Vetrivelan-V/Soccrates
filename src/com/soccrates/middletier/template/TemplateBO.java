package com.soccrates.middletier.template;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateBO extends SoccratesObject {
	private long templateId;
	private long coachId;

	private String templateName;

	private ArrayList<CategoryBO> criteria;

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public ArrayList<CategoryBO> getCriteria() {
		return criteria;
	}

	public void setCriteria(ArrayList<CategoryBO> criteria) {
		this.criteria = criteria;
	}

	@Override
	public String toString() {
		return "TemplateBO [templateId=" + templateId + ", coachId=" + coachId + ", templateName=" + templateName
				+ ", criteria=" + criteria + ", id=" + ", getTemplateId()=" + getTemplateId() + ", getCoachId()="
				+ getCoachId() + ", getTemplateName()=" + getTemplateName() + ", getCriteria()=" + getCriteria()
				+ ", getExceptionId()=" + getExceptionId() + ", isException()=" + isException() + ", getMessage()="
				+ getMessage() + ", getId()=" + getId() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public void copy(TemplateEntity data) {
		// TODO Auto-generated method stub
		this.templateId = data.getTemplateId();
		this.coachId = data.getCoach().getUserId();
		this.templateName = data.getTemplateName();
	}

}
