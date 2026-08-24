package com.bhavesh16281.accounts.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.dto.CustomerDetailsDto;
import com.bhavesh16281.accounts.dto.ErrorResponseDTO;
import com.bhavesh16281.accounts.service.AccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(name = "Customers API", description = "API for managing customer details")
@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerController {

    private final AccountsService accountsService;

    public CustomerController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Operation(
            summary = "Fetch customer details by phone number",
            description = "Retrieves customer details based on the provided phone number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer details fetched successfully"),
            @ApiResponse(responseCode = "500",description = "Internal server error while fetching customer details",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetchDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be a 10-digit number") String mobileNumber) {
        
        CustomerDetailsDto customerDetailsDto = accountsService.getCustomerDetailsByPhone(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);
    }
       
}
