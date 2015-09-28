/**
* Classname : FileOperations
* Functionality Description : This class exposes common used operations required 
* to ADD/DELETE and search records.
* Modification Log
* Date                        Author                      Description
* --------------------------------------------------------------------------
* 31-Oct-2014                Tripti Tantri               Baseline Version
* Release no : 1.0
*/
package com.info.portal.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.info.portal.beans.CustomerInfo;

public class FileOperations {

	@Autowired
	private FileUtil fileUtil;

	/**
	 * This method returns all the json strings present in the flat file
	 * @return List<CustomerInfo>
	 */
	public List<CustomerInfo> searchAll() {

		List<CustomerInfo> custList = fileUtil.ReadFromFile();

		return custList;
	}
	/**
	 * This method implements the add logic and calls the FileUtil to
	 * write into flat file
	 * @param customerInfoList
	 * @param custToAdd
	 * @return List<CustomerInfo>
	 */
	public List<CustomerInfo> add(List<CustomerInfo> customerInfoList,
			CustomerInfo custToAdd) {

		int temp = 0;
		if (customerInfoList.size() > 0) {
			for (CustomerInfo cust : customerInfoList) {
				if (temp < cust.getCustID()) {
					temp = cust.getCustID();
				}
			}
			custToAdd.setCustID(++temp);
		} else {
			custToAdd.setCustID(temp);
		}
		customerInfoList.add(custToAdd);
		fileUtil.writeToFile(customerInfoList);

		return customerInfoList;
	}
	/**
	 * This method implements the delete logic and calls the FileUtil to 
	 * write into file
	 * @param customerInfoList
	 * @param custToDelete
	 * @return List<CustomerInfo>
	 */
	public List<CustomerInfo> delete(List<CustomerInfo> customerInfoList,
			CustomerInfo custToDelete) {

		boolean isDeleteSuccess = false;
		for (CustomerInfo cust : customerInfoList) {
			if (cust.getCustID() == custToDelete.getCustID()) {
				customerInfoList.remove(cust);
				isDeleteSuccess = true;
				break;
			}
		}
		if(isDeleteSuccess){
		fileUtil.writeToFile(customerInfoList);
		return customerInfoList;
		}
		return null;
	}
}
