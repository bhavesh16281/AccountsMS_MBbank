package com.bhavesh16281.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response",
        description = "Schema to hold response status code and message")
public class ResponseDTO {

    @Schema(name = "statusCode",
            description = "Status code of the response")
    private String statusCode;

    @Schema(name = "statusMessage",
            description = "Status message of the response")
    private String statusMessage;

}
