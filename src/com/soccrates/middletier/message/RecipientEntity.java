package com.soccrates.middletier.message;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.user.UserEntity;

@Entity
@Table(name = "recipient")
public class RecipientEntity {

	private MessageEntity messageId;
	private long recipientId;
	private int status;
	private Date createdDate;
	private UserEntity toId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	public long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
	}
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "messageId")
	public MessageEntity getMessageId() {
		return messageId;
	}

	public void setMessageId(MessageEntity messageId) {
		this.messageId = messageId;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public UserEntity getToId() {
		return toId;
	}

	public void setToId(UserEntity toId) {
		this.toId = toId;
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

	 

	

}
