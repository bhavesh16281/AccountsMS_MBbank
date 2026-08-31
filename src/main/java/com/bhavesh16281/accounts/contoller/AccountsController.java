package com.bhavesh16281.accounts.contoller;

import com.bhavesh16281.accounts.constants.AccountsConstants;
import com.bhavesh16281.accounts.dto.AccountsContactInfoDto;
import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.dto.ErrorResponseDTO;
import com.bhavesh16281.accounts.dto.ResponseDTO;
import com.bhavesh16281.accounts.service.AccountsService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts API", description = "API for managing customer accounts")
@RestController
@RequestMapping(value = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final AccountsService accountsService;
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    public  AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Operation(
            summary = "Get Build Information",
            description = "Get Build information that is deployed into accounts microservice.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "500",description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @Retry(name = "getBuildInfo",fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() throws TimeoutException{
        logger.debug("inside getBuildInfo()");
        // throw new NullPointerException();
        throw new TimeoutException();
        // return ResponseEntity.ok(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable){
        
        logger.debug("inside getBuildInfoFallback()");
        return ResponseEntity.status(HttpStatus.OK)
                .body("retry method invoked.");
    }

    @Operation(
            summary = "Get Java Version",
            description = "Get Java version that is deployed into accounts microservice.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "500",description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @RateLimiter(name = "getJavaInfo",fallbackMethod = "getJavaInfoFallback")
    @GetMapping("/java-info")
    public ResponseEntity<String> getJavaInfo(){
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaInfoFallback(Throwable throwable){
        return ResponseEntity.status(HttpStatus.OK).body("Java 17");
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact info if there is any issue in the API.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "500",description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity.ok(accountsContactInfoDto); 
    }

    @Operation(
            summary = "Create a new customer account",
            description = "Creates a new customer account with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error while creating account",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO  customerDTO){
        accountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account details by phone number",
            description = "Retrieves account details based on the provided phone number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details fetched successfully"),
            @ApiResponse(responseCode = "500",description = "Internal server error while fetching account details",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> getCustomerByPhone(@RequestParam
                                                              @Pattern(regexp = "(^|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                              String phone){

        CustomerDTO customerDTO = accountsService.getCustomerByPhone(phone);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);
    }

    @Operation(
            summary = "Update existing customer account",
            description = "Updates the details of an existing customer account.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation failed while updating account"),
            @ApiResponse(responseCode = "500", description = "Internal server error while updating account",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO  customerDTO){

        boolean isUpdated = accountsService.updateAccount(customerDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete customer account by phone number",
            description = "Deletes a customer account based on the provided phone number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation failed while deleting account"),
            @ApiResponse(responseCode = "500", description = "Internal server error while deleting account",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String phone){

        boolean isDeleted = accountsService.deleteAccount(phone);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
