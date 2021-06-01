package com.thymeleaf.crudthymeleafdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleaf.crudthymeleafdemo.entity.Employee;
import com.thymeleaf.crudthymeleafdemo.repo.EmployeeRepo;
import com.thymeleaf.crudthymeleafdemo.repo.PageAndSortSupport;

@Service
public class EmployeeService {
	// field injection of repo or dao object

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PageAndSortSupport repoWitPage;

	@Transactional
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAllEmployees();
	}

	@Transactional
	public Employee findEmployeeUsingID(Integer id) {
		return employeeRepo.findEmployeeUsingID(id);
	}

	@Transactional
	public void saveEmployee(Employee employee) {
		employeeRepo.saveEmployee(employee);
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		employeeRepo.deleteEmployee(id);
	}

	public List<Employee> findAllWithPage(Integer pageNo, Integer pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = repoWitPage.findAll(page);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Employee>();
		}
	}

}
