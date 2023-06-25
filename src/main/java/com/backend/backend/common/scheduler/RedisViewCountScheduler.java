package com.backend.backend.common.scheduler;

import com.backend.backend.common.configuration.redis.RedisKey;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisViewCountScheduler {
    private final RedisTemplate<String, String> redisTemplate;

    private final PostRepository postRepository;

    /**
     * 1시간 마다 작동
     */
    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateVisitorData() {
        Set<String> keys = redisTemplate.keys(RedisKey.PostViewCount + "_*");

        for(String key:keys){
            String[] parts = key.split("_");
            String postId = parts[1];

            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String viewCount = valueOperations.get(key);

            if(viewCount!=null){
                Post post = postRepository.findPostById(Long.parseLong(postId));
                post.addViewCount(Long.parseLong(viewCount));
            }
            redisTemplate.delete(key);
        }
    }
}
