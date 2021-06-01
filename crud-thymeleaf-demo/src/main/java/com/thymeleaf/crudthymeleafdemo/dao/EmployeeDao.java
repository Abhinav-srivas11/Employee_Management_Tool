package com.thymeleaf.crudthymeleafdemo.dao;

import java.util.List;

import com.thymeleaf.crudthymeleafdemo.entity.Employee;

public interface EmployeeDao {

	public List<Employee> findAllEmployees();

	public Employee findEmployeeUsingID(Integer id);

	public void saveEmployee(Employee employee);

	public void deleteEmployee(Integer id);
}
