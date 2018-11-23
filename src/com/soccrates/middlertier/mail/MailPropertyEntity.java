package com.soccrates.middlertier.mail;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class MailPropertyEntity.
 */
@Entity(name="mail_property")
public class MailPropertyEntity {
	
	/** The property id. */
	String propertyId;

/** The property name. */
String propertyName;

/** The property value. */
String propertyValue;

/** The mail id. */
MailPropertyEntity mailId;

/** The type. */
int type;

/**
 * Gets the type.
 *
 * @return the type
 */
public int getType() {
	return type;
}

/**
 * Sets the type.
 *
 * @param type the new type
 */
public void setType(int type) {
	this.type = type;
}

/**
 * Gets the property id.
 *
 * @return the property id
 */
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
public String getPropertyId() {
	return propertyId;
}

/**
 * Sets the property id.
 *
 * @param propertyId the new property id
 */
public void setPropertyId(String propertyId) {
	this.propertyId = propertyId;
}

/**
 * Gets the mail id.
 *
 * @return the mail id
 */
@ManyToOne(cascade=CascadeType.REFRESH)
@JoinColumn(name="mailId")
public MailPropertyEntity getMailId() {
	return mailId;
}

/**
 * Sets the mail id.
 *
 * @param mailId the new mail id
 */
public void setMailId(MailPropertyEntity mailId) {
	this.mailId = mailId;
}

/**
 * Gets the property name.
 *
 * @return the property name
 */
public String getPropertyName() {
	return propertyName;
}

/**
 * Sets the property name.
 *
 * @param propertyName the new property name
 */
public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}

/**
 * Gets the property value.
 *
 * @return the property value
 */
public String getPropertyValue() {
	return propertyValue;
}

/**
 * Sets the property value.
 *
 * @param propertyValue the new property value
 */
public void setPropertyValue(String propertyValue) {
	this.propertyValue = propertyValue;
}

}
