package com.bhavesh16281.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponse",
        description = "Schema to hold error response details")
public class ErrorResponseDTO {

    @Schema(name = "apiPath",
            description = "The API endpoint that was accessed when the error occurred"
            )
    private String apiPath;

    @Schema(name = "errorCode",
            description = "The HTTP status code representing the type of error that occurred"
    )
    private HttpStatus errorCode;

    @Schema(name = "errorMessage",
            description = "A descriptive message providing details about the error that occurred"
    )
    private String errorMessage;

    @Schema(name = "errorTime",
            description = "The timestamp indicating when the error occurred"
    )
    private LocalDateTime errorTime;
}
