package arturo3393.dev.employeemanayer.service;

import arturo3393.dev.employeemanayer.domain.Employee;
import arturo3393.dev.employeemanayer.repository.ResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    List<Employee> findAllEmployees();

    Optional<Employee> findEmployeeById(Long employeeId);

    ResponseDTO<Employee> addEmployee(Employee employee);

    ResponseDTO<Employee> updateEmployee(Employee employee);

    ResponseDTO<Void>  deleteEmployee(Long employeeId);
}
