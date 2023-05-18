package com.backend.backend.repository.postRepository;

import com.backend.backend.repository.OrderBy;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder
@Data
public class PostSearch {
    private String postName;
    private OrderBy orderBy;
    private Long limit;

}
