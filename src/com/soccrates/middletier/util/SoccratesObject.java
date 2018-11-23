package com.soccrates.middletier.util;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoccratesObject {
	private int exceptionId;
	private String message;
	private boolean isException = false;

	long id;

	public int getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(int exceptionId) {
		this.exceptionId = exceptionId;
	}
 
	 
	public boolean isException() {
		return isException;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(boolean isException) {
		this.isException = isException;
	}

	@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SoccratesObject [exceptionId=" + exceptionId + ", message=" + message + ", isException=" + isException
				+ ", id=" + id + "]";
	}

}
