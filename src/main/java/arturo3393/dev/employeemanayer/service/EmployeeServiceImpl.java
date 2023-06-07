package arturo3393.dev.employeemanayer.service;

import arturo3393.dev.employeemanayer.domain.Employee;
import arturo3393.dev.employeemanayer.exceptions.UserNotFoundException;
import arturo3393.dev.employeemanayer.repository.EmployeeRepository;
import arturo3393.dev.employeemanayer.repository.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseDTO<Employee> addEmployee(Employee employee){
        Optional<Employee> storedEmployed =  employeeRepository.findById(employee.getId());
        ResponseDTO<Employee> response = new ResponseDTO<>();
        if(storedEmployed.isPresent()){
            response.setData(null);
            response.setMsg("Employee " + storedEmployed.get().getName() + " is already in the BD");
            response.setAnswer(false);
            return response;
        }
        employee.setEmployeeCode(UUID.randomUUID().toString());
         employeeRepository.save(employee);
        response.setAnswer(true);
        response.setData(employee);
        response.setMsg("Employee "+ employee.getName() + " was sucessfully saved");
        return response;
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee (Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(Long employeeId){
        return Optional.ofNullable(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException("User with the id " + employeeId + " was not found")));
    }

    public void deleteEmployee (Long employeeId){
        employeeRepository.deleteById(employeeId);
    }


}
