/**
* Classname : CustomerInfo
* Functionality Description : This is the customer bean to hold
* customer record details
* Modification Log
* Date                        Author                      Description
* --------------------------------------------------------------------------
* 31-Oct-2014                Tripti Tantri               Baseline Version
* Release no : 1.0
*/
package com.info.portal.beans;


public class CustomerInfo {
	private String name;
	private String phone;
	private String mail;
	private int custID;
	private AddressDetails address;
	
	CustomerInfo(){
		
	}
	/**
	 * @return the custID
	 */
	public int getCustID() {
		return custID;
	}
	/**
	 * @param custID the custID to set
	 */
	public void setCustID(int custID) {
		this.custID = custID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the address
	 */
	public AddressDetails getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressDetails addressdetails) {
		this.address = addressdetails;
		}
	
	public String toString()
	{
		String result;
		result = this.getName() + " " + this.getPhone() + " " + this.getMail() + " " + this.getAddress().getStreet() + " " + this.getAddress().getCity() + " " + this.getAddress().getState() + " " + this.getAddress().getZip() ;
		return result;
	}

}
