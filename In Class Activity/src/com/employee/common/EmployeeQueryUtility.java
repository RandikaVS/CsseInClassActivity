package com.employee.common;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EmployeeQueryUtility extends BaseUtility {
	
	//constants
	
	public static final String Query = "query";
	public static final String Id = "id";
	
	/**
	 * Get the employee query
	 * 
	 * @return query
	 * @throws Exception
	 *             null exception(in case of the XML loading failed)
	 */
	
	public static String getEmployeeQueryById(String id) throws Exception {
		
		NodeList nodeList; 
		Element element = null;
		
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(Constance.EMPLOYEE_QUERY_XML))
				.getElementsByTagName(Query);
		
		for (int x = 0; x < nodeList.getLength(); x++) {
			
			element = (Element) nodeList.item(x);
			
			if (element.getAttribute(Id).equals(id))
				break;
		}
		
		return element.getTextContent().trim();
	}

}
