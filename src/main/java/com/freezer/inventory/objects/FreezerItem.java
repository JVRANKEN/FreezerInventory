package com.freezer.inventory.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreezerItem {
    private String documentId;
    private String item;
    // EXAMPLE: vlees, groenten, vis
    private String category;
    // Example kip
    private String type;
    private Long quantity;
    // weigth = in gram
    private Long weight;
    private Date expiryDate;
    private Date frozenDate;
    private Long maxMonths;
    private String comment;
    private Boolean existingItem;

    @Override
    public String toString() {
        return "<" + "item=" + item + ", category=" + category + ", type=" + type + ", comment=" + comment + ">";
    }
}
