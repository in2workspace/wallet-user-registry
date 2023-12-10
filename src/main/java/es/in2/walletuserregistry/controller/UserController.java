package es.in2.walletuserregistry.controller;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.facade.UserRegistryFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static es.in2.walletuserregistry.utils.MessageUtils.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistryFacadeService userRegistryFacadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an entity", description = "Creates a new entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = RESPONSE_CODE_201_DESCRIPTION),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = RESPONSE_CODE_400_DESCRIPTION),
            @ApiResponse(responseCode = RESPONSE_CODE_401, description = RESPONSE_CODE_401_DESCRIPTION),
            @ApiResponse(responseCode = RESPONSE_CODE_403, description = RESPONSE_CODE_403_DESCRIPTION),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = RESPONSE_CODE_500_DESCRIPTION)
    })
    public Mono<Void> registerUser(@RequestBody UserRegistryRequest userRegistryRequest) {
        String processId = UUID.randomUUID().toString();
        return userRegistryFacadeService.createUser(processId, userRegistryRequest);
    }

}
