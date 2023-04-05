package com.backend.backend.repository.postRepository;

import com.backend.backend.repository.OrderBy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder
public class PostSearch {
    private String postName;
    private OrderBy orderBy;

}
