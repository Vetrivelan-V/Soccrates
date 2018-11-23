package com.soccrates.middletier.message;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientBO extends SoccratesObject {
	private long messageId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	private long recipientId;
	private int status;
	private Date createdDate;
	private long toId;
	private String name;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}

	public void copy(RecipientEntity iterable_element) {
		// TODO Auto-generated method stub
		this.messageId = iterable_element.getMessageId().getMessageId();
		this.recipientId = iterable_element.getRecipientId();
		this.status = iterable_element.getStatus();
		this.createdDate = iterable_element.getCreatedDate();
		this.toId = iterable_element.getToId().getUserId();
		this.name=iterable_element.getToId().getFirstName()+" "+iterable_element.getToId().getLastName();
	}

}
