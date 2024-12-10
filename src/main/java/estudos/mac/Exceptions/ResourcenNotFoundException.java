package estudos.mac.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourcenNotFoundException extends RuntimeException {
    public ResourcenNotFoundException(String message){
        super(message);
    }
}
