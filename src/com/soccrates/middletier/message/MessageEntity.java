package com.soccrates.middletier.message;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "message")
public class MessageEntity {

	private TeamEntity teamEntity;
	private long messageId;
	private int messageType;
	private String body;
	private String subject;
	private UserEntity senderId;
	private Date createdDate= new Date();
	private List<RecipientEntity> recipientList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public UserEntity getSenderId() {
		return senderId;
	}

	public void setSenderId(UserEntity senderId) {
		this.senderId = senderId;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "teamId")
	public TeamEntity getTeamEntity() {
		return teamEntity;
	}

	public void setTeamEntity(TeamEntity teamEntity) {
		this.teamEntity = teamEntity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	 

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = RecipientEntity.class, mappedBy = "messageId")
	@JoinColumn(name = "messageId")
	public List<RecipientEntity> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<RecipientEntity> recipientList) {
		this.recipientList = recipientList;
	}

	public void copy(MessageBO userBo) {
		// TODO Auto-generated method stub
		 
		
		this.messageType=userBo.getMessageType();
		this.body=userBo.getBody();
		this.subject=userBo.getSubject();
		this.createdDate= new Date();
	 
	}

}
