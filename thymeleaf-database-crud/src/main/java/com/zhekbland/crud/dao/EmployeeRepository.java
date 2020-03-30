package com.zhekbland.crud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhekbland.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findAllByOrderByLastNameAsc();
}
