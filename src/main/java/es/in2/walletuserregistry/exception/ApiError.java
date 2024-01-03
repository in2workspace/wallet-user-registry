package es.in2.walletuserregistry.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class ApiError {
    private String title;
    private String message;
    private HttpStatus httpStatus;
    private String path;

    public ApiError(String title, String message, HttpStatus httpStatus, String paths) {
        super();
        this.title = title;
        this.message = message;
        this.httpStatus = httpStatus;
        this.path = paths;
    }

}