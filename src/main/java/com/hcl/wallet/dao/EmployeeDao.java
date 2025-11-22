package com.hcl.wallet.dao;

import com.hcl.wallet.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee saveData(Employee employee);

    Employee getDataById(int empId);

    List<Employee> getAllData();

    void deleteEmployeeById(int empId);
}