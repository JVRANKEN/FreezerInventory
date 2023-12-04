package com.freezer.inventory.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freezer.inventory.service.FreezerServiceInternalTesting;
import com.freezer.inventory.utility.UrlMapping;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(UrlMapping.FREEZER_INTERNAL_TESTING + UrlMapping.SEARCH)
public class FreezerControllerInternalTesting {
    @Autowired
    private FreezerServiceInternalTesting freezerServiceInternalTesting;

    public FreezerControllerInternalTesting(final FreezerServiceInternalTesting freezerServiceInternalTesting) {
        this.freezerServiceInternalTesting = freezerServiceInternalTesting;
    }


    @Operation(summary = "Export all the data to a json")
    @GetMapping(UrlMapping.EXPORT_FIREBASE)
    public ResponseEntity<String> getAllFreezerItemsExportJSON() throws ExecutionException, InterruptedException, JsonProcessingException {

        final String exportResult = freezerServiceInternalTesting.exportDatabaseItemsToJSON();

        return new ResponseEntity<>(exportResult, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test GET endpoint is working!");
    }
}
