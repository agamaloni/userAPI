package com.example.userAPIbackend.web;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.exception.SqlNotAvailableException;
import com.example.userAPIbackend.exception.UserNotFoundException;
import com.example.userAPIbackend.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Users API",description = "An API that can manage Usres", version = "v1"))
@RestController
public class UserController {
    private UserService userService;

    @Operation(summary = "Retrieves users", description = "Provides a list of all users")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @Operation(summary = "Create User", description = "Creates a user from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of user",useReturnTypeSchema = true,content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful update",useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = MethodArgumentNotValidException.class))),
            @ApiResponse(responseCode = "451", description = "DB not reachable", useReturnTypeSchema = true,content = @Content(schema = @Schema(implementation = SqlNotAvailableException.class)))
    })
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Operation(summary = "Delete User", description = "Delete user from the provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful deleted the user"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful Delete", useReturnTypeSchema = true,content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
            @ApiResponse(responseCode = "451", description = "DB not reachable", useReturnTypeSchema = true,content = @Content(schema = @Schema(implementation = SqlNotAvailableException.class)))
    })
    @DeleteMapping(value ="/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
