package com.backend.backend.common.scheduler;

import com.backend.backend.common.configuration.redis.RedisKey;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisViewCountScheduler {
    private final RedisTemplate<String, String> redisTemplate;

    private final PostRepository postRepository;

    private final PopularPostRepository popularPostRepository;

    /**
     * 1분 마다 작동
     */
    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void updateVisitorData() {
        Set<String> keys = redisTemplate.keys(RedisKey.PostViewCount + "_*");
        //최소힙
        PriorityQueue<PopularPost> popularPosts = new PriorityQueue<>();

        for(String key:keys){
            String[] parts = key.split("_");
            String postId = parts[1];

            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String viewCount = valueOperations.get(key);

            if(viewCount!=null){
                Post post = postRepository.findPostById(Long.parseLong(postId));
                post.addViewCount(Long.parseLong(viewCount));

                //인기게시글 선정
                popularPosts.add(new PopularPost(post, Long.parseLong(viewCount)));
                if(popularPosts.size()>10){
                    popularPosts.poll();
                }
            }
            redisTemplate.delete(key);
        }

        changePopularPost(popularPosts);
    }


    private void changePopularPost(PriorityQueue<PopularPost> popularPostPq){
        List<PopularPost> popularPostList = new ArrayList<>(popularPostPq);
        popularPostRepository.changePopularPostList(popularPostList);
    }
}
