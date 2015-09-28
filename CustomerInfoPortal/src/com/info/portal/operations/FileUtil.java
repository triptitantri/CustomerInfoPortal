/**
 * Classname : FileUtil
 * Functionality Description : This class implements methods exposed
 * by jackson-json API. Jackson databind feature is used to write/read
 * customer records into a flat file in the json format.
 * Modification Log
 * Date                        Author                      Description
 * --------------------------------------------------------------------------
 * 31-Oct-2014                Tripti Tantri               Baseline Version
 * Release no : 1.0
 */
package com.info.portal.operations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.portal.beans.CustomerInfo;

public class FileUtil {

	@Value("#{appProperties['JSON_DATAFILE_PATH']}")
	private String jsonDataFilePath;
	Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * Jackson-json call to write POJO as json string into flat file
	 * 
	 * @param CustomerInfoList
	 */
	public void writeToFile(List<CustomerInfo> CustomerInfoList) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			logger.info(jsonDataFilePath);
			// convert user object to json string, and save to a file
			mapper.writeValue(new File(jsonDataFilePath), CustomerInfoList);

		} catch (JsonGenerationException jsonGenerationException) {

			jsonGenerationException.printStackTrace();

		} catch (JsonMappingException jsonMappingException) {

			jsonMappingException.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Jackson-json call to convert json string as POJO
	 * 
	 * @return List<CustomerInfo>
	 */
	public List<CustomerInfo> ReadFromFile() {

		List<CustomerInfo> myObjects = new ArrayList<CustomerInfo>();

		try {

			ObjectMapper mapper = new ObjectMapper();
			myObjects = mapper.readValue(new File(jsonDataFilePath),
					new TypeReference<List<CustomerInfo>>() {
					});
			for (CustomerInfo cust : myObjects) {
				// cust.setName("testing");
				System.out.println(cust);
			}

		} catch (JsonGenerationException jsonGenerationException) {

			jsonGenerationException.printStackTrace();

		} catch (JsonMappingException jsonMappingException) {

			jsonMappingException.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return myObjects;

	}

}
