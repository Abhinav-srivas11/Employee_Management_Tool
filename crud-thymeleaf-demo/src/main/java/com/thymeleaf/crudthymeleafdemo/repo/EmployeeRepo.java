package com.thymeleaf.crudthymeleafdemo.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thymeleaf.crudthymeleafdemo.dao.EmployeeDao;
import com.thymeleaf.crudthymeleafdemo.entity.Employee;

@Repository
public class EmployeeRepo implements EmployeeDao {

	@Autowired
	private EntityManager entityMan;

	@Override
	public List<Employee> findAllEmployees() {
		Session session = entityMan.unwrap(Session.class);

		Query<Employee> query = session.createQuery("from Employee", Employee.class);

		List<Employee> list = query.getResultList();

		return list;

	}

	@Override
	public Employee findEmployeeUsingID(Integer id) {
		Session session = entityMan.unwrap(Session.class);

		Query query = session.createQuery("from Employee where id=:theID");
		query.setParameter("theID", id);

		Employee employee = (Employee) query.getSingleResult();
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		Session session = entityMan.unwrap(Session.class);

		session.saveOrUpdate(employee);

	}

	@Override
	public void deleteEmployee(Integer id) {
		Session session = entityMan.unwrap(Session.class);

		Query query = session.createQuery("delete from Employee where id=:theID");
		query.setParameter("theID", id);

		query.executeUpdate();

	}

}
