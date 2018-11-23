package com.soccrates.middletier.address;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressBO extends SoccratesObject {
	private long addressId;

	/** The address 2. */
	private String houseNumber, address1, address2;

	/** The street. */
	private String street;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/** The zip code. */
	private String zipCode;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "AddressBO [addressId=" + addressId + ", houseNumber=" + houseNumber + ", address1=" + address1
				+ ", address2=" + address2 + ", street=" + street + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipCode=" + zipCode + "]";
	}

	public void copy(AddressEntity teamAddress) {
		// TODO Auto-generated method stubthis

		this.setAddress1(teamAddress.getAddress1());
		this.setAddress2(teamAddress.getAddress2());
		this.setAddressId(teamAddress.getAddressId());
		this.setCity(teamAddress.getCity());
		this.setCountry(teamAddress.getCountry());
		this.setHouseNumber(teamAddress.getHouseNumber());
		this.setState(teamAddress.getState());
		this.setStreet(teamAddress.getStreet());
		this.setZipCode(teamAddress.getZipCode());

	}

}
