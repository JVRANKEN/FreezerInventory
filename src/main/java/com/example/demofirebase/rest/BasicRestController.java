package com.example.demofirebase.rest;

import com.example.demofirebase.objects.Person;
import com.example.demofirebase.service.PersonCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/person")
public class BasicRestController {
    @Autowired
    private PersonCrudService personCrudService;

    public BasicRestController(PersonCrudService personCrudService) {
        this.personCrudService = personCrudService;
    }

//    @Operation(summary = "Get a person by the id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the person",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Person.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)})
//    @GetMapping(UrlRedirect.ID)
//    public ResponseEntity<Person> findPersonForID(@RequestParam final Long id) {
//        Person result = myService.findPersonForId(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
    @PostMapping("/create")
    public String createCrudPerson(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return personCrudService.createCRUDPerson(person);
    }

    @GetMapping("/get")
    public Person getCrudPerson(@RequestParam String objectId) throws ExecutionException, InterruptedException {
        return personCrudService.getCRUDPerson(objectId);
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return personCrudService.updateCRUDPerson(person);
    }

    @DeleteMapping("/delete")
    public String deleteCRUD(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return personCrudService.deleteCRUDPerson(documentId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test GET endpoint is working!");
    }
}
