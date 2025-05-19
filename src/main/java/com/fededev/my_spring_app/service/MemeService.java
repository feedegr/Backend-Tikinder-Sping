package com.fededev.my_spring_app.service;

import com.fededev.my_spring_app.dto.request.CreateMemeDTO;
import com.fededev.my_spring_app.exception.ResourceNotFoundException;

import com.fededev.my_spring_app.dto.response.MemeDTO;
import com.fededev.my_spring_app.model.Meme;
import com.fededev.my_spring_app.repository.MemeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class MemeService {
    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public MemeDTO getMemeById(Long id) {
        Meme meme = memeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meme not found with id: " + id));
        return new MemeDTO(meme);
    }

    public List<MemeDTO> getAllMemes() {
        try {
            List<Meme> memes = memeRepository.findAll();
            return memes.stream()
                    .map(MemeDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving memes", e);
        }
    }

    public void deleteMeme(Long id) {
        memeRepository.deleteById(id);
    }

    public MemeDTO updateMeme(Long id, @Valid CreateMemeDTO updateMemeDTO) {
         Meme existingMeme = memeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meme not found with id: " + id));

        existingMeme.setTitle(updateMemeDTO.getTitle());
        existingMeme.setImageUrl(updateMemeDTO.getImageUrl());

        Meme updatedMeme = memeRepository.save(existingMeme);
        return new MemeDTO(updatedMeme);
    }

    public MemeDTO createMeme(@Valid CreateMemeDTO createMemeDTO) {
        Meme meme = new Meme();
        meme.setTitle(createMemeDTO.getTitle());
        meme.setImageUrl(createMemeDTO.getImageUrl());
        Meme savedMeme = memeRepository.save(meme);
        return new MemeDTO(savedMeme);
    }
}
