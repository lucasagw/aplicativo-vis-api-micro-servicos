package api.vis.authorizationserver.util.error;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ErrorObject> errors;

}
