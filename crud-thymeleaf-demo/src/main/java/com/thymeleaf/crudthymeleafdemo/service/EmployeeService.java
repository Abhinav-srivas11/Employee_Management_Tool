package com.thymeleaf.crudthymeleafdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleaf.crudthymeleafdemo.entity.Employee;
import com.thymeleaf.crudthymeleafdemo.repo.EmployeeRepo;

@Service
public class EmployeeService {
	// field injection of repo or dao object

	@Autowired
	private EmployeeRepo employeeRepoDao;

	@Transactional
	public List<Employee> findAllEmployees() {
		return employeeRepoDao.findAllEmployees();
	}

	@Transactional
	public Employee findEmployeeUsingID(Integer id) {
		return employeeRepoDao.findEmployeeUsingID(id);
	}

	@Transactional
	public void saveEmployee(Employee employee) {
		employeeRepoDao.saveEmployee(employee);
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		employeeRepoDao.deleteEmployee(id);
	}
}
