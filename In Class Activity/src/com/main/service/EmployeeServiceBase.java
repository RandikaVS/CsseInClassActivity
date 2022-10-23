package com.main.service;


import java.util.logging.Logger;

import com.employee.common.BaseUtility;
import com.employee.common.EmployeeTransformationUtility;

/**
 * Abstract of Employee service base
 * 
 */

public abstract class EmployeeServiceBase extends BaseUtility {
	
	public abstract void employeesFromXML();

	public abstract void createEmployeeTable();

	public abstract void addEmployees();

	public abstract void displayEmployees();

	/**
	 * This method is used for initialization of data and printing the employees
	 * table template
	 */
	
	final public void displayEmployeesTemplateMethod() {

		final Logger log = Logger.getLogger(EmployeeServiceBase.class.getName());
		
		try {
			
			EmployeeTransformationUtility.requestTransform();
			
			employeesFromXML();
			createEmployeeTable();
			addEmployees();
			displayEmployees();
			
		} catch (Exception e) {
			
			log.info("Error occured when executing the Template method: " + e.getMessage() + "\n");
		}

	}

}
