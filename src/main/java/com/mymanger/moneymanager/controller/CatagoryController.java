package com.mymanger.moneymanager.controller;

import com.mymanger.moneymanager.dto.CatagoryDTO;
import com.mymanger.moneymanager.service.CatagoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catagories")
public class CatagoryController {

    private final CatagoryService catagoryService;
    @PostMapping
    public ResponseEntity<CatagoryDTO> saveCategory(@RequestBody CatagoryDTO categoryDTO) {
        CatagoryDTO savedCategory = catagoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CatagoryDTO>> getCategories() {
        List<CatagoryDTO> categories = catagoryService.getCategoriesForCurrentUser();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{type}")
    public ResponseEntity<List<CatagoryDTO>> getCategoriesByTypeForCurrentUser(@PathVariable String type) {
        List<CatagoryDTO> list = catagoryService.getCategoriesByTypeForCurrentUser(type);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CatagoryDTO> updateCategory(@PathVariable Long categoryId,
                                                      @RequestBody CatagoryDTO categoryDTO) {
        CatagoryDTO updatedCategory = catagoryService.updateCategory(categoryId, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

}
