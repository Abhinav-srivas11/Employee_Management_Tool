package com.thymeleaf.crudthymeleafdemo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thymeleaf.crudthymeleafdemo.entity.Employee;

public interface PageAndSortSupport extends PagingAndSortingRepository<Employee, Integer> {

}
