package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee getEmployee(int employeeId) {
		Optional<Employee> result = employeeRepository.findById(employeeId);

		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + employeeId);
		}

		return theEmployee;
	}

	@Override
	public void addOrUpdateEmployee(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteEmployee(int theId) {
		employeeRepository.deleteById(theId);

	}

}
