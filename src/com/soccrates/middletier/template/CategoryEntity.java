package com.soccrates.middletier.template;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
// TODO: Auto-generated Javadoc
/**
 * The Class MSCCategoryEntity.
 */
@Entity
@Table(name="category")
public class CategoryEntity {

	
	/** The msc parent id. */
	 
	/** The retailer id. */
	private TemplateEntity templateId;
	
	/** The category name. */
	private String categoryName;
	
	/** The updated date. */
	private Date creationDate,updatedDate;
	
	/** The category description. */
	private String categoryDescription;
	
	/** The parent id. */
	private CategoryEntity parentId;
	
	/** The category id. */
	private long categoryId;
	
 
	/** The sub categories. */
	private List<CategoryEntity>subCategories;
	
 	
	/**
	 * Gets the msc parent id.
	 *
	 * @return the msc parent id
	 */
  	/** The level. */
	private int level;
	
	/**
	 * Gets the msc category id.
	 *
	 * @return the msc category id
	 */
	
	 
	 
	
	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	 
	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Gets the sub categories.
	 *
	 * @return the sub categories
	 */
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity=CategoryEntity.class,mappedBy="parentId")
	@JoinColumn(name="parentId")
	public List<CategoryEntity> getSubCategories() {
		return subCategories;
	}
	
	/**
	 * Sets the sub categories.
	 *
	 * @param subCategories the new sub categories
	 */
	public void setSubCategories(List<CategoryEntity> subCategories) {
		this.subCategories = subCategories;
	}
	
  	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * Sets the category name.
	 *
	 * @param categoryName the new category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * Gets the category description.
	 *
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}
	
	/**
	 * Sets the category description.
	 *
	 * @param categoryDescription the new category description
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentId")
	public CategoryEntity getParentId() {
		return parentId;
	}
	
	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(CategoryEntity parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="templateId",nullable=true)
	public TemplateEntity getTemplateId() {
		return templateId;
	}

	public void setTemplateId(TemplateEntity templateId) {
		this.templateId = templateId;
	}
	
}
