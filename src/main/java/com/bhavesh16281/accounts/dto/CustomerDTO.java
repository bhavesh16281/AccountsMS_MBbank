package com.bhavesh16281.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Customer",
        description = "Schema to hold Customer and Account information")
public class CustomerDTO {

    @Schema(name = "name",
    description = "Name of the customer",
    example = "John Doe")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5,max = 30,message = "The length of name must be between 5 and 30")
    private String name;

    @Schema(name = "email",
    description = "Email address of the customer",
    example = "email@xyz.com")
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Enter valid email")
    private String email;

    @Schema(name = "phone",
    description = "Phone number of the customer",
    example = "9876543210")
    @Pattern(regexp = "(^|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phone;

    @Schema(
    description = "Account details of the customer")
    private AccountsDTO accountsDTO;
}
