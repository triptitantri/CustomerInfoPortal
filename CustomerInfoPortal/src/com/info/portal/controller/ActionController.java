/**
* Classname : ActionController
* Functionality Description : This is the controller class which calls appropriate 
* actions to perform as requested from the client. Data between client and this
* controller is exchanged in json format
* Modification Log
* Date                        Author                      Description
* --------------------------------------------------------------------------
* 31-Oct-2014                Tripti Tantri               Baseline Version
* Release no : 1.0
*/
package com.info.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.info.portal.beans.CustomerInfo;
import com.info.portal.operations.FileOperations;

@Controller
public class ActionController {
	Logger logger = LoggerFactory.getLogger(ActionController.class);
	@Autowired
	private FileOperations fileOperation;
	
	/**
	 * This method is called initially when the application url is launched
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/CustomerData", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		
		return "CustomerData";

	}

	/**
	 * This method is called when add customer request is sent from client
	 * @param customerinfo
	 * @return CustomerInfo
	 */
	@RequestMapping(headers = "Content-Type=application/json", method = RequestMethod.POST, value = "/addCustomer")
	@ResponseBody
	public CustomerInfo addCustomer(@RequestBody CustomerInfo customerinfo) {
		logger.info("Adding Customer Data...");
		List<CustomerInfo> custListSearchResult = fileOperation.searchAll();
		fileOperation.add(custListSearchResult, customerinfo);

		return customerinfo;
	}

	/**
	 * This method is called when search customer request is sent from client
	 * @param customerinfo
	 * @return List<CustomerInfo>
	 */
	@RequestMapping(headers = "Content-Type=application/json", method = RequestMethod.POST, value = "/getCustomer")
	@ResponseBody
	public List<CustomerInfo> getCustomer(@RequestBody CustomerInfo customerinfo) {
		logger.info("Fetching Customer Data...");
		List<CustomerInfo> custlistSearchResult = new ArrayList<CustomerInfo>();
		List<CustomerInfo> custlist = fileOperation.searchAll();

		for (CustomerInfo cust : custlist) {

			if (cust.getName().contains(customerinfo.getName())
					&& (cust.getMail().equalsIgnoreCase(customerinfo.getMail()) || cust
							.getPhone().equalsIgnoreCase(
									customerinfo.getPhone()))) {
				custlistSearchResult.add(cust);
			}
		}
		return custlistSearchResult;
	}
	/**
	 * This method called when delete customer request is sent from client
	 * @param customerinfo
	 * @return List<CustomerInfo>
	 */
	@RequestMapping(headers = "Content-Type=application/json", method = RequestMethod.POST, value = "/delCustomer")
	@ResponseBody
	public List<CustomerInfo> delCustomer(@RequestBody CustomerInfo customerinfo) {
		logger.info("Delting Customer Data...");

		List<CustomerInfo> custListSearchResult = fileOperation.searchAll();
		List<CustomerInfo> custlistAfterDelete = new ArrayList<CustomerInfo>();
		custlistAfterDelete = fileOperation.delete(custListSearchResult,
				customerinfo);
		if (custlistAfterDelete != null)
		{
		List<CustomerInfo> custlistSearchSearchMatch = new ArrayList<CustomerInfo>();

		for (CustomerInfo cust : custlistAfterDelete) {

			if (cust.getName().contains(customerinfo.getName())
					&& (cust.getMail().equalsIgnoreCase(customerinfo.getMail()) || cust
							.getPhone().equalsIgnoreCase(
									customerinfo.getPhone()))) {
				custlistSearchSearchMatch.add(cust);
			}
		}
		return custlistSearchSearchMatch;
		}
		return null;
	}
	/**
	 * This method is called when update request is sent from client
	 * @param customerinfo
	 * @return
	 */
	@RequestMapping(headers = "Content-Type=application/json", method = RequestMethod.POST, value = "/updateCustomer")
	@ResponseBody
	public List<CustomerInfo> updateCustomer(
			@RequestBody CustomerInfo customerinfo) {
		logger.info("Updating Customer Data...");
		List<CustomerInfo> custListSearchResult = fileOperation.searchAll();
		List<CustomerInfo> custlistAfterDelete = fileOperation.delete(
				custListSearchResult, customerinfo);
		if(custlistAfterDelete != null)
		{
		List<CustomerInfo> custlistAfterUpdate = fileOperation.add(
				custlistAfterDelete, customerinfo);
		return custlistAfterUpdate;
		}
		return null;
	}
}
