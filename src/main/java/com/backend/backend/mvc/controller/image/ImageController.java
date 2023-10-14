package com.backend.backend.mvc.controller.image;

import com.backend.backend.mvc.domain.image.Image;
import com.backend.backend.mvc.repository.imageRepository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/image")
public class ImageController {

    private final ImageRepository imageRepository;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long imageId) {
        // byte 배열로부터 이미지 생성
        Image image = imageRepository.findImageById(imageId);

        byte[] imageBytes = null;
        if(image!=null) {
            imageBytes = image.getImage();
        }

        // 이미지와 함께 응답 생성
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}
