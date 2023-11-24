package com.freezer.inventory.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreezerItem {
    @Id
    private Long id;
    private String item;
    private String category;
    private Long quantity;
    // weigth = in gram
    private Date expiryDate;
    private Date frozenDate;
    private Long maxMonths;

}
