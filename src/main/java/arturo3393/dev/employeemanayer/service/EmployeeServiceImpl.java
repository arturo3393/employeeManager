package arturo3393.dev.employeemanayer.service;

import arturo3393.dev.employeemanayer.domain.Employee;
import arturo3393.dev.employeemanayer.exceptions.UserNotFoundException;
import arturo3393.dev.employeemanayer.repository.EmployeeRepository;
import arturo3393.dev.employeemanayer.repository.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseDTO<Employee> addEmployee(Employee employee){
        Optional<Employee> storedEmployed =  employeeRepository.findByEmail(employee.getEmail());
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

    public ResponseDTO<Employee> updateEmployee (Employee employee){ //pending
        ResponseDTO<Employee> response = new ResponseDTO<>();
        Optional<Employee> storedEmployed =  employeeRepository.findById(employee.getId());
        if(storedEmployed.isPresent()){
            storedEmployed.get().setName(Objects.isNull(employee.getName())?  storedEmployed.get().getName() : employee.getName());
            storedEmployed.get().setEmail(Objects.isNull(employee.getEmail())?  storedEmployed.get().getEmail() : employee.getEmail());
            storedEmployed.get().setJobTittle(Objects.isNull(employee.getJobTittle())?  storedEmployed.get().getJobTittle() : employee.getJobTittle());
            storedEmployed.get().setPhone(Objects.isNull(employee.getPhone())?  storedEmployed.get().getPhone() : employee.getPhone());
            storedEmployed.get().setImageUrl(Objects.isNull(employee.getImageUrl())?  storedEmployed.get().getImageUrl() : employee.getImageUrl());

            //employeeRepository.save(employee);
            response.setMsg("Employee with the id "+employee.getId()+ " was successfully updated");
            response.setAnswer(true);
            return  response;
        }
        response.setAnswer(false);
        response.setMsg("Employee with the id "+employee.getId()+ " was not in the BD");
        return response;
    }

    public Optional<Employee> findEmployeeById(Long employeeId){
        return Optional.ofNullable(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException("User with the id " + employeeId + " was not found")));
    }

    public ResponseDTO<Void> deleteEmployee (Long employeeId){
        ResponseDTO<Void> response = new ResponseDTO<>();
        Optional<Employee> storedEmployed =  employeeRepository.findById(employeeId);
        if(storedEmployed.isPresent()){
            employeeRepository.deleteById(employeeId);
            response.setMsg("Employee " + storedEmployed.get().getName() + " was successfully deleted");
            response.setAnswer(true);
            return  response;
        }

        response.setAnswer(false);
        response.setMsg("Employee with the id "+employeeId+ " was not in the BD");
        return response;

    }


}
