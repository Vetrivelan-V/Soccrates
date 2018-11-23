package com.soccrates.middletier.message;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageBO extends SoccratesObject {
	private long teamId;
	private long messageId;
	private int messageType;
	private String body;
	private String subject;
	private long senderId;
	private String createdDate;
	private String senderName;
	private List<RecipientBO> recipientList;

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<RecipientBO> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<RecipientBO> recipientList) {
		this.recipientList = recipientList;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void copy(MessageEntity coachEntity) {
		// TODO Auto-generated method stub
		if (coachEntity.getTeamEntity() != null)
			this.teamId = coachEntity.getTeamEntity().getTeamId();
		if (coachEntity.getCreatedDate() != null)
			this.createdDate = ApplicationConstants.DATE_WITH_TIME.format(coachEntity.getCreatedDate());
		this.messageId = coachEntity.getMessageId();
		this.messageType = coachEntity.getMessageType();
		this.body = coachEntity.getBody();
		this.subject = coachEntity.getSubject();
		this.senderId = coachEntity.getSenderId().getUserId();
		this.senderName=coachEntity.getSenderId().getFirstName() +" " +coachEntity.getSenderId().getLastName();

	}

}
