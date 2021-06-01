package com.thymeleaf.crudthymeleafdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymeleaf.crudthymeleafdemo.entity.Employee;
import com.thymeleaf.crudthymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class CrudController {

	// inject dependency for employee service
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public String tableOfEmployees(Model model) {
		model.addAttribute("employees", employeeService.findAllEmployees());

		return "employee-page";
	}

	@GetMapping("/hello")
	public String helloPage() {
		return "hello-world";
	}

	@GetMapping("/employees/showUpdateForm")
	public String showFormForUpdate(Model model, @RequestParam("employeeID") int employeeID) {
		Employee employee = employeeService.findEmployeeUsingID(employeeID);
		model.addAttribute("employeeByID", employee);
		return "empl-update-form";

	}

	@PostMapping("/employees")
	public String updateEmployee(@ModelAttribute("employeeByID") Employee emp, Model theModel) {
		System.out.println(emp.getId());
		employeeService.saveEmployee(emp);
		theModel.addAttribute("employees", employeeService.findAllEmployees());
		return "redirect:/api/employees";

	}

	@GetMapping("/employees/new")
	public String showFormForSavin(Model theModel) {
		Employee employee = new Employee();
		theModel.addAttribute("employeeByID", employee);
		return "empl-save-form";

	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employeeByID") Employee emp) {
		employeeService.saveEmployee(emp);
		return "redirect:/api/employees";
	}

	@GetMapping("/employees/delete")
	public String deleteEmployee(@RequestParam("employeeID") int employeeID) {
		employeeService.deleteEmployee(employeeID);
		return "redirect:/api/employees";
	}

}
