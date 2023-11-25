package com.freezer.inventory.rest;

import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;
import com.freezer.inventory.utility.UrlMapping;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(UrlMapping.FREEZER)
public class FreezerController {
    @Autowired
    private FreezerService freezerService;

    public FreezerController(FreezerService personCrudService) {
        this.freezerService = personCrudService;
    }

//    @Operation(summary = "Get a person by the id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the person",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Person.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)})

    @Operation(summary = "Create an item for the freezer")
    @PostMapping("/create")
    public String createFreezerItem(@RequestBody FreezerItem freezerItem) throws ExecutionException, InterruptedException {
        return freezerService.createFreezerItem(freezerItem);
    }

    @GetMapping("/get/item")
    public ResponseEntity<List<FreezerItem>> getFreezerItemByItem(@RequestParam String item) throws ExecutionException, InterruptedException {

        List<FreezerItem> freezerItemList =  freezerService.getFreezerItemByItemName(item);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public String updateFreezerItem(@RequestBody FreezerItem person) throws ExecutionException, InterruptedException {
        return freezerService.updateFreezerItem(person);
    }

    @DeleteMapping("/delete")
    public String deleteFreezerItem(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return freezerService.deleteFreezerItem(documentId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test GET endpoint is working!");
    }
}
