package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String getEmployees(Model theModel) {
		List<Employee> theEmployees = employeeService.getEmployees();
		theModel.addAttribute("employees", theEmployees);

		// get the username authenticated
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername(); // get logged in username
		theModel.addAttribute("username", name);

		// get the role of the username
		Collection<GrantedAuthority> roles = user.getAuthorities();
		theModel.addAttribute("roles", roles);

		return "employees/list-employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAddEmployee(Model theModel) {
		Employee newEmployee = new Employee();
		theModel.addAttribute("employee", newEmployee);
		return "employees/add-form-employee";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// Jpa repository will know already if it needs to add or update depending if it
		// has id or not
		employeeService.addOrUpdateEmployee(theEmployee);
		return "redirect:/employees/list";

	}

	@GetMapping("/shorFormForUpdate")
	public String shorFormForUpdateEmployee(@RequestParam("employeeId") int theId, Model theModel) {
		Employee theEmployee = employeeService.getEmployee(theId);
		theModel.addAttribute("employee", theEmployee);
		return "employees/add-form-employee";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteEmployee(theId);
		return "redirect:/employees/list";
	}

}
