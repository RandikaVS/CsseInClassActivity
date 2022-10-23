package com.employee.modal;

public class EmployeeModal {
	
	//declare variables
	
	public String employeeId;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;
	
	//setters
	
	public void setEmployeeId(String employeeID) {
		this.employeeId = employeeID;
	}
	
	public void setEmployeeFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setEmployeeFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	public void setEmployeeAddress(String address) {
		this.address = address;
	}
	
	public void setEmployeeDepartment(String department) {
		this.department = department;
	}
	
	public void setEmployeedesignation(String designation) {
		this.designation = designation;
	}
	
	
	//getters
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public String getEmployeeFullName() {
		return fullName;
	}
	
	public String getEmployeeAddress() {
		return address;
	}
	
	public String getEmployeefacultyName() {
		return facultyName;
	}
	
	public String getEmployeeDepartment() {
		return department;
	}
	
	public String getEmployeeDesignation() {
		return designation;
	}
	
	
	
	//convert employee details to string
	
	@Override
	public String toString() {
		
		return "Employee ID = " + employeeId + "\n" + "FullName = " + fullName + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}

}
