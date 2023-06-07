package arturo3393.dev.employeemanayer.controller;

import arturo3393.dev.employeemanayer.domain.Employee;
import arturo3393.dev.employeemanayer.repository.ResponseDTO;
import arturo3393.dev.employeemanayer.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeService emloyeeService;

    @GetMapping("/employees")
    public ResponseEntity<ResponseDTO<List<Employee>>> getAllEmployees (){
        ResponseDTO<List<Employee>> response = new ResponseDTO<>();

        try{
            List<Employee> employees = emloyeeService.findAllEmployees();
            response.setData(employees);
            response.setMsg("Successfully retrieved");
            response.setAnswer(true);

        } catch (Exception e){
            response.setAnswer(false);
            response.setMsg("Fail: "+ e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<ResponseDTO<Optional<Employee>>> getEmployeeById (@PathVariable("employeeId") Long employeeId){
        ResponseDTO<Optional<Employee>> response = new ResponseDTO<>();
        try{
            Optional<Employee> employee = emloyeeService.findEmployeeById(employeeId);
            if(employee.isPresent()){
            response.setData(employee);
            response.setMsg("Successfully retrieved");
            response.setAnswer(true);
            }
            else {
                response.setMsg("The employee " + employee + " is not in the BD");
                response.setAnswer(true);
            }

        } catch (Exception e){
            response.setAnswer(false);
            response.setMsg("Fail: "+ e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping ("/addEmployee")
    public ResponseEntity<ResponseDTO<Employee>> addEmployee (@RequestBody Employee employee){
        ResponseDTO<Employee> response = new ResponseDTO<>();

        try{
            emloyeeService.addEmployee(employee);
            response.setData(employee);
            response.setAnswer(true);
            response.setMsg("The employee " + employee.getName() + " was saved");

        } catch (Exception e){
            response.setAnswer(false);
            response.setMsg("Fail: "+ e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
