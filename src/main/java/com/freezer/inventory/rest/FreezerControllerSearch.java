package com.freezer.inventory.rest;

import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;
import com.freezer.inventory.utility.UrlMapping;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(UrlMapping.FREEZER + UrlMapping.SEARCH)
// TODO search for a better fix for the cors error
@CrossOrigin(origins = "*")
public class FreezerControllerSearch {
    @Autowired
    private FreezerService freezerService;

    public FreezerControllerSearch(final FreezerService personCrudService) {
        this.freezerService = personCrudService;
    }

//    @Operation(summary = "Get a person by the id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the person",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Person.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)})

    @Operation(summary = "Get all items from the freezer")
    @GetMapping()
    public ResponseEntity<List<FreezerItem>> getAllFreezerItems() throws ExecutionException, InterruptedException {

        final List<FreezerItem> freezerItemList = freezerService.getAllFreezerItems();

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    @Operation(summary = "Get an item by item property")
    @GetMapping(UrlMapping.GET_ITEM)
    public ResponseEntity<List<FreezerItem>> getFreezerItemByItem(@RequestParam final String item) throws ExecutionException, InterruptedException {

        final List<FreezerItem> freezerItemList = freezerService.getFreezerItemByItemName(item);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    @Operation(summary = "Get an item by type property")
    @GetMapping(UrlMapping.GET_TYPE)
    public ResponseEntity<List<FreezerItem>> getFreezerItemByType(@RequestParam final String type) throws ExecutionException, InterruptedException {

        final List<FreezerItem> freezerItemList = freezerService.getFreezerItemByType(type);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    @Operation(summary = "Get an item by category property")
    @GetMapping(UrlMapping.GET_CATEGORY)
    public ResponseEntity<List<FreezerItem>> getFreezerItemByCategory(@RequestParam final String category) throws ExecutionException, InterruptedException {

        final List<FreezerItem> freezerItemList = freezerService.getFreezerItemByCategory(category);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    /**
     * Get all items untill the date, get also all items untill date + 2 weeks
     *
     * @param dateExpiry
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Operation(summary = "Get all items untill expiry date")
    @GetMapping(UrlMapping.GET_EXPIRYDATE)
    public ResponseEntity<List<FreezerItem>> getFreezerItemByDateExpire(@RequestParam final String dateExpiry) throws ExecutionException, InterruptedException, ParseException {

        final List<FreezerItem> freezerItemList = freezerService.getFreezerItemByDateExpire(dateExpiry);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    /**
     * Get all items untill the frozen date + add maxMonths, also plus 2 weeks??
     *
     * @param date
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Operation(summary = "Get all items untill expiry date and items untill expiry date + twoo weeks")
    @GetMapping(UrlMapping.GET_FROZENDATE_ADD_MONTHS)
    public ResponseEntity<List<FreezerItem>> getFreezerItemByFrozenDateAndMaxMonths(@RequestParam final String date, @RequestParam final int extraMonths) throws ExecutionException, InterruptedException, ParseException {

        final List<FreezerItem> freezerItemList = freezerService.getFreezerItemByFrozenDateAndMaxMonths(date, extraMonths);

        return new ResponseEntity<>(freezerItemList, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test GET endpoint is working!");
    }
}
