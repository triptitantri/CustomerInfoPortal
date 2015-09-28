/**
* Classname : AddressDetails
* Functionality Description : This is the address bean to hold
* customer address fields
* Modification Log
* Date                        Author                      Description
* --------------------------------------------------------------------------
* 31-Oct-2014                Tripti Tantri               Baseline Version
* Release no : 1.0
*/
package com.info.portal.beans;

public class AddressDetails {

	private String street;
	private String city;
	private String state;
	private String zip;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String toString() {
		String result;
		result = this.getStreet() + " " + this.getCity() + " "
				+ this.getState() + " " + this.getZip();
		return result;
	}

}
