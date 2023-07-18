package com.backend.backend.mvc.repository.imageRepository;

import com.backend.backend.mvc.domain.image.Image;

public interface ImageRepository {

    /**
     * 이미지를 저장
     * @param image 이미지 객체
     * @return 저장한 이미지의 id
     */
    Long save(Image image);

    /**
     * 이미지를 id를 이용해서 탐색
     * @param id 이미지의 id
     * @return 이미지 객체
     */
    Image findImageById(Long id);
}
