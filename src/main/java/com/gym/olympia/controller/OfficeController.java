package com.gym.olympia.controller;


import com.gym.olympia.entity.Office;
import com.gym.olympia.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    OfficeService officeService;


    @GetMapping
    public List<Office> listALl() {
        return officeService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> findBy(@PathVariable Long id) {
        return officeService.findID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Office create(@RequestBody Office office) {
        return officeService.save(office);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> update(@PathVariable Long id, @RequestBody Office office) {
        try {
            Office officeActualizada = officeService.update(id, office);
            return ResponseEntity.ok(officeActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        officeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
