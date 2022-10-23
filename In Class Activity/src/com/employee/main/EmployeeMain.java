package com.employee.main;

import com.employee.common.EmployeeTransformationUtility;
import com.main.service.EmployeeService;

public class EmployeeMain {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		//create an employee object by calling constructor
		EmployeeService employeeService = new EmployeeService();
		
		try {
			
			EmployeeTransformationUtility.requestTransform();
			
			employeeService.employeesFromXML();
			employeeService.createEmployeeTable();
			employeeService.addEmployees();
			employeeService.displayEmployees();
			
		} catch (Exception e) {
		}

	}

}
