package com.hcl.wallet.service;

import com.hcl.wallet.dto.EmployeeDTO;
import com.hcl.wallet.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveData(EmployeeDTO employeeDTO);

    Employee updateData(int empId, EmployeeDTO employeeDTO);

    Employee getDataById(int empId);

    List<Employee> getAllData();

    void deleteEmployeeById(int empId);
}