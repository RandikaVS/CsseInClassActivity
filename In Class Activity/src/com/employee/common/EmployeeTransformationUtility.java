package com.employee.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class EmployeeTransformationUtility extends BaseUtility {
	
	
	/**
	 * XMLPath constants
	 * 
	 */
	public static final String COUNT_EMPLOYEES_EMPLOYEE_EXPRESSION = "count(//Employees/Employee)";
	public static final String EMPLOYEE_ID = "/EmployeeID";
	public static final String EMPLOYEE_FULL_NAME = "/EmployeeFullName";
	public static final String EMPLOYEE_FACULTY_NAME = "/FacultyName";
	public static final String EMPLOYEE_DEPARTMENT = "/Department";
	public static final String EMPLOYEE_DESIGNATION = "/Designation";
	public static final String EMPLOYEE_ADDRESS = "/EmployeeFullAddress";

	
	private static final ArrayList<Map<String, String>> employeesReceivedInfo = new ArrayList<Map<String, String>>();

	private static Map<String, String> employeesInfo = null;

	public static void requestTransform() throws Exception {

		Source requestSource = new StreamSource(new File(Constance.EMPLOYEE_REQUEST_XML));
		Source modifiedSource = new StreamSource(new File(Constance.EMPLOYEE_MODIFIED_XSL));
		Result streamResult = new StreamResult(new File(Constance.EMPLOYEE_RESPONSE_XML));
		
		TransformerFactory.newInstance().newTransformer(modifiedSource).transform(requestSource, streamResult);
	}
	
	/**
	 * Create an array for all the XML Paths form employees
	 * 
	 * @return ArrayList<Map<String, String>> where each Map's key referring to
	 *         employee field name and value referring to its values
	 * @throws Exception
	 *             NullException if the files not found or wrong paths are given
	 */

	public static ArrayList<Map<String, String>> getAllXmlPaths() throws Exception {

		Document employeeResponseDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(Constance.EMPLOYEE_RESPONSE_XML2);
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		int employeeCount = Integer.parseInt((String) xPath.compile(COUNT_EMPLOYEES_EMPLOYEE_EXPRESSION)
				.evaluate(employeeResponseDocument, XPathConstants.STRING));
		
		for (int i = 1; i <= employeeCount; i++) {
			
			employeesInfo = new HashMap<String, String>();
			
			employeesInfo.put(Constance.XPATH_EMPLOYEE_ID_KEY, (String) xPath.compile(xPathExpression(i, EMPLOYEE_ID))
					.evaluate(employeeResponseDocument, XPathConstants.STRING));
			
			employeesInfo.put(Constance.XPATH_EMPLOYEE_NAME_KEY, (String) xPath.compile(xPathExpression(i, EMPLOYEE_FULL_NAME))
					.evaluate(employeeResponseDocument, XPathConstants.STRING));
			
			employeesInfo.put(Constance.XPATH_EMPLOYEE_ADDRESS_KEY,(String) xPath.compile(xPathExpression(i, EMPLOYEE_ADDRESS))
					.evaluate(employeeResponseDocument,XPathConstants.STRING));
			
			employeesInfo.put(Constance.XPATH_FACULTY_NAME_KEY, (String) xPath.compile(xPathExpression(i, EMPLOYEE_FACULTY_NAME))
					.evaluate(employeeResponseDocument, XPathConstants.STRING));
			
			employeesInfo.put(Constance.XPATH_DEPARTMENT_KEY, (String) xPath.compile(xPathExpression(i, EMPLOYEE_DEPARTMENT))
					.evaluate(employeeResponseDocument, XPathConstants.STRING));
			
			employeesInfo.put(Constance.XPATH_DESIGNATION_KEY, (String) xPath.compile(xPathExpression(i, EMPLOYEE_DESIGNATION))
					.evaluate(employeeResponseDocument, XPathConstants.STRING));
			employeesReceivedInfo.add(employeesInfo);
		}
		return employeesReceivedInfo;
	}

	private static String xPathExpression(int i, String employeeXPathField) {
		// TODO Auto-generated method stub
		return Constance.EMPLOYEES_EMPLOYEE_ROOT_PATH + i + "]" + employeeXPathField + "/text()";
	}
	}

}
