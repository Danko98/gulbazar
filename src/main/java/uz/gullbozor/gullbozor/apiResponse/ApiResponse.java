package uz.gullbozor.gullbozor.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApiResponse {

    private String massage;
    private boolean success = true;
    private Object object;

    private Long attachContactId;
    private UUID mainAttachId;

    public ApiResponse(Long generatedLong, UUID id) {

    }

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }
}
