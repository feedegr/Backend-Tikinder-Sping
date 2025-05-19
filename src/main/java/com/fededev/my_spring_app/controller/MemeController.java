package com.fededev.my_spring_app.controller;

import com.fededev.my_spring_app.dto.request.CreateMemeDTO;
import com.fededev.my_spring_app.dto.response.MemeDTO;
import com.fededev.my_spring_app.model.Meme;
import com.fededev.my_spring_app.service.MemeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/memes")
public class MemeController {

    @Autowired
    private MemeService memeService;

    @GetMapping
    public ResponseEntity<List<MemeDTO>> getAllMemes() {
        List<MemeDTO> memes = memeService.getAllMemes();
        return ResponseEntity.ok(memes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeDTO> getMemeById(@PathVariable Long id) {
        MemeDTO meme = memeService.getMemeById(id);
        return ResponseEntity.ok(meme);
    }

    @PostMapping
    public ResponseEntity<MemeDTO> createMeme(@Valid @RequestBody CreateMemeDTO createMemeDTO) {
        MemeDTO createdMeme = memeService.createMeme(createMemeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeme);
    }

    // Si tienes endpoints de actualización y eliminación
    @PutMapping("/{id}")
    public ResponseEntity<MemeDTO> updateMeme(
            @PathVariable Long id,
            @Valid @RequestBody CreateMemeDTO updateMemeDTO) {
        MemeDTO updatedMeme = memeService.updateMeme(id, updateMemeDTO);
        return ResponseEntity.ok(updatedMeme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeme(@PathVariable Long id) {
        memeService.deleteMeme(id);
        return ResponseEntity.noContent().build();
    }
}
