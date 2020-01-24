package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public Employee getEmployee(int employeeId);

	public void addOrUpdateEmployee(Employee theEmployee);

	public void deleteEmployee(int theId);

}
