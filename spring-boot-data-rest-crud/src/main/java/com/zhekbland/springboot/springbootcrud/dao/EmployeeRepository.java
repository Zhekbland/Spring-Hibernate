package com.zhekbland.springboot.springbootcrud.dao;

import com.zhekbland.springboot.springbootcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
