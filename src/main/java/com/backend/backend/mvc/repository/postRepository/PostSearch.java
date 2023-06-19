package com.backend.backend.mvc.repository.postRepository;

import com.backend.backend.mvc.repository.OrderBy;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter @Builder
@Data
public class PostSearch {
    private String postName;
    private OrderBy orderBy;
    private Long limit;

}
