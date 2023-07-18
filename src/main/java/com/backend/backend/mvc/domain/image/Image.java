package com.backend.backend.mvc.domain.image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue
    private Long id;

    @Lob
    private byte[] image;

    public Image(byte[] image) {
        if(image==null){
            throw new IllegalArgumentException("저장할 이미지가 없습니다");
        }
        this.image = image;
    }
}
