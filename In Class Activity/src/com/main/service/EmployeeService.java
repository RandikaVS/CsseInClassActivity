package com.main.service;

import java.io.IOException;
import java.util.logging.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import com.employee.common.BaseUtility;
import com.employee.common.EmployeeQueryUtility;
import com.employee.common.EmployeeTransformationUtility;
import com.employee.modal.EmployeeModal;

public class EmployeeService extends BaseUtility {
	
	private final ArrayList<EmployeeModal> employeeArrayList = new ArrayList<EmployeeModal>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;
	
	private static final Logger log = Logger.getLogger(EmployeeService.class.getName());
	
	public static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "url";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	private static EmployeeService employeeServiceInstance = null;


	
	//employeeService class constructor

	public EmployeeService() {
		try {
			Class.forName(COM_MYSQL_JDBC_DRIVER);
			connection = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USERNAME),
					properties.getProperty(PASSWORD));
		} 
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		} 
	}
	
	public static EmployeeService getInstance() {
		if (employeeServiceInstance == null) {
			synchronized (EmployeeService.class) {
				if (employeeServiceInstance == null) {
					employeeServiceInstance = new EmployeeService();
				}
			}

		}
		return employeeServiceInstance;
	}
	

	public void employeesFromXML() {

		try {
			
			int s = EmployeeTransformationUtility.getAllXmlPaths().size();
			
			for (int i = 0; i < s; i++) {
				
				Map<String, String> xml = EmployeeTransformationUtility.getAllXmlPaths().get(i);
				
				EmployeeModal employee = new EmployeeModal();
				
				employee.setEmployeeId(xml.get("XpathEmployeeIDKey"));
				employee.setEmployeeFullName(xml.get("XpathEmployeeNameKey"));
				employee.setEmployeeAddress(xml.get("XpathEmployeeAddressKey"));
				employee.setEmployeeFacultyName(xml.get("XpathFacultyNameKey"));
				employee.setEmployeeDepartment(xml.get("XpathDepartmentKey"));
				employee.setEmployeedesignation(xml.get("XpathDesignationKey"));
				
				employeeArrayList.add(employee);
				
				System.out.println(employee.toString() + "\n");
			}
		}
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
	}

	public void createEmployeeTable() {
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(EmployeeQueryUtility.getEmployeeQueryById("q2"));
			statement.executeUpdate(EmployeeQueryUtility.getEmployeeQueryById("q1"));
		}
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		}  
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
		}
	}

	public void addEmployees() {
		
		try {
			
			preparedStatement = connection.prepareStatement(EmployeeQueryUtility.getEmployeeQueryById("q3"));
			connection.setAutoCommit(false);
			
			for(int i = 0; i < employeeArrayList.size(); i++){
				
				EmployeeModal e = employeeArrayList.get(i);
				
				preparedStatement.setString(1, e.getEmployeeId());
				preparedStatement.setString(2, e.getEmployeeFullName());
				preparedStatement.setString(3, e.getEmployeeAddress());
				preparedStatement.setString(4, e.getEmployeefacultyName());
				preparedStatement.setString(5, e.getEmployeeDepartment());
				preparedStatement.setString(6, e.getEmployeeDesignation());
				
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			connection.commit();
			
		}
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
	}

	public void getEmployeeById(String eid) {

		EmployeeModal employee = new EmployeeModal();
		try {
			preparedStatement = connection.prepareStatement(EmployeeQueryUtility.getEmployeeQueryById("q4"));
			preparedStatement.setString(1, eid);
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				
				employee.setEmployeeId(result.getString(1));
				employee.setEmployeeFullName(result.getString(2));
				employee.setEmployeeAddress(result.getString(3));
				employee.setEmployeeFacultyName(result.getString(4));
				employee.setEmployeeDepartment(result.getString(5));
				employee.setEmployeedesignation(result.getString(6));
			}
			
			ArrayList<EmployeeModal> employeeModal = new ArrayList<EmployeeModal>();
			
			employeeModal.add(employee);
			
			printEmployeesOutput(employeeModal);
			
		} 
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
	}

	public void deleteEmployees(String eid) {

		try {
			
			preparedStatement = connection.prepareStatement(EmployeeQueryUtility.getEmployeeQueryById("q6"));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
			
		} 
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
	}

	public void displayEmployees() {

		ArrayList<EmployeeModal> employeeList = new ArrayList<EmployeeModal>();
		
		try {
			
			preparedStatement = connection.prepareStatement(EmployeeQueryUtility.getEmployeeQueryById("q5"));
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				
				EmployeeModal employeeModal = new EmployeeModal();
				
				employeeModal.setEmployeeId(result.getString(1));
				employeeModal.setEmployeeFullName(result.getString(2));
				employeeModal.setEmployeeAddress(result.getString(3));
				employeeModal.setEmployeeFacultyName(result.getString(4));
				employeeModal.setEmployeeDepartment(result.getString(5));
				employeeModal.setEmployeedesignation(result.getString(6));
				
				employeeList.add(employeeModal);
			}
		} 
		catch (NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (XPathExpressionException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.ERROR, e.getMessage());
		} 
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
		
		printEmployeesOutput(employeeList);
	}
	
	public void printEmployeesOutput(ArrayList<EmployeeModal> arrayList){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < arrayList.size(); i++){
			EmployeeModal e = arrayList.get(i);
			System.out.println(e.getEmployeeId() + "\t" + e.getEmployeeFullName() + "\t\t"
					+ e.getEmployeeAddress() + "\t" + e.getEmployeefacultyName() + "\t" + e.getEmployeeDepartment() + "\t"
					+ e.getEmployeeDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}

}
