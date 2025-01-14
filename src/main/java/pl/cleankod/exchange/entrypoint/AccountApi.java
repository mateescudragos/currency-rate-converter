package pl.cleankod.exchange.entrypoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cleankod.exchange.core.domain.Account;

@Tag(name = "Account", description = "The Account API")
public interface AccountApi {
    @Operation(summary = "Finds an account by a given Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Account founded."),
                    @ApiResponse(responseCode = "404", description = "Account cannot be found", content = @Content)
            }
    )
    @GetMapping(path = "/{id}")
    ResponseEntity<Account> findAccountById(@PathVariable String id, @RequestParam(required = false) String currency);

    @Operation(summary = "Finds an account by a given account number")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Account founded."),
                    @ApiResponse(responseCode = "404", description = "Account cannot be found", content = @Content)
            }
    )
    @GetMapping(path = "/number={number}")
    ResponseEntity<Account> findAccountByNumber(@PathVariable String number, @RequestParam(required = false) String currency);
}
