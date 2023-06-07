package arturo3393.dev.employeemanayer.repository;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO<T> implements Serializable {
    private boolean answer;
    private String msg;
    private T data = null;
}
