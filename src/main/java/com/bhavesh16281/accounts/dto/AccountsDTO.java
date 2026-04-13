package com.bhavesh16281.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Accounts",
        description = "Schema to hold Account details of the customer")
public class AccountsDTO {

    @Schema(name = "accountNumber",
    description = "Account number of the customer")
    @Pattern(regexp = "(^|[0-9]{10})",message = "Account number must be 10 digits")
    @NotEmpty(message = "Account number cannot be null or empty")
    private Long accountNumber;

    @Schema(name = "accountType",
    description = "Type of the account")
    @NotEmpty(message = "Account type cannot be null")
    private String accountType;

    @Schema(name = "branchAddress",
    description = "Branch address of the account",
    example = "123 Main St, City, Country")
    @NotEmpty(message = "Branch Address cannot be null or empty")
    private String branchAddress;
}
