package arturo3393.dev.employeemanayer.repository;

import arturo3393.dev.employeemanayer.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail (String email);
}
