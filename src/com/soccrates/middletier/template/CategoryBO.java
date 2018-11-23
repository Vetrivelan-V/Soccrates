package com.soccrates.middletier.template;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryBO extends SoccratesObject {

	
	private long categoryId;
	private long parentId;
	private String categoryName;
//	private BigDecimal minValue , maxValue ;
	private ArrayList<CategoryBO> subCategories; 
    private long templateId;
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ArrayList<CategoryBO> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(ArrayList<CategoryBO> subCategories) {
		this.subCategories = subCategories;
	}
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	@Override
	public String toString() {
		return "CategoryBO [categoryId=" + categoryId + ", parentId=" + parentId + ", categoryName=" + categoryName
				+ ", subCategories=" + subCategories + ", templateId=" + templateId + "]";
	}
	
}
