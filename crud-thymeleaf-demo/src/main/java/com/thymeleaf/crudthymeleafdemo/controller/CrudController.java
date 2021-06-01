package com.thymeleaf.crudthymeleafdemo.controller;

import java.util.List;

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

//	@GetMapping("/employees")
//	public String tableOfEmployees(Model model) {
//		model.addAttribute("employees", employeeService.findAllEmployees());
//
//		return "employee-page";
//	}

	// Alternate way that supports paging
	@GetMapping("/employees")
	// we have passed default values to our page number and page size integers so
	// that we do not leave it null and cause errors
	public String tableOfEmployees(Model model, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		// we have defined the method in service layer that intakes pageNo and pageSize
		// parameters to create a Pageable object
		List<Employee> listEmployees = employeeService.findAllWithPage(pageNo, pageSize);
		// adding the list of employees to our employees attribute in the model
		model.addAttribute("employees", listEmployees);
		// since we need to work with page numbers for navigation purposes
		// i have added the single attribute to model making it easier to access values
		model.addAttribute("page", pageNo);
		// calculating the max number of pages that are allowed
		Integer numOfPages = (listEmployees.size() / pageSize) + 1;
		// adding it to second attribute in our model
		// second attribute is required to make sure that previous and next are shown on
		// the webapp only when page number is within limit
		model.addAttribute("numOfPages", numOfPages);
		return "employee-page";
	}

	@GetMapping("/hello")
	public String helloPage() {
		//
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
