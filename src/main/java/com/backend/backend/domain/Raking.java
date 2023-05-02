package com.backend.backend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Raking {

    @Id @GeneratedValue @Column(name = "raking_id")
    Long id;
    Long category1=0L;
    Long category2=0L;
    Long category3=0L;
    Long category4=0L;
}
