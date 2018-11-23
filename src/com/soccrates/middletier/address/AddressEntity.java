/*
 * 
 */
package com.soccrates.middletier.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.soccrates.middletier.constant.ApplicationTableConstants;

// TODO: Auto-generated Javadoc

/**
 * The Class AddressEntity.
 *
 * @author bvbi-infotech
 */
@Entity
@Table(name = "address")
public class AddressEntity {

	/** The address id. */
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

	/**
	 * Gets the address 1.
	 *
	 * @return the address 1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the address 1.
	 *
	 * @param address1
	 *            the new address 1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Gets the address 2.
	 *
	 * @return the address 2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets the address 2.
	 *
	 * @param address2
	 *            the new address 2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Gets the address id.
	 *
	 * @return the address id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getAddressId() {
		return addressId;
	}

	/**
	 * Sets the address id.
	 *
	 * @param addressId
	 *            the new address id
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	/**
	 * Gets the house number.
	 *
	 * @return the house number
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Sets the house number.
	 *
	 * @param houseNumber
	 *            the new house number
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	@Column(length = ApplicationTableConstants.NAMESIZE)
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode
	 *            the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void copy(AddressBO addressBO) {
		this.setAddress1(addressBO.getAddress1());
		;
		this.setAddress2(addressBO.getAddress2());
		;
		this.setCity(addressBO.getCity());
		this.setCountry(addressBO.getCountry());
		;
		this.setHouseNumber(addressBO.getHouseNumber());
		this.setState(addressBO.getState());
		this.setZipCode(addressBO.getZipCode());

	}

}