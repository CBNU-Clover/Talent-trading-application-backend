package com.backend.backend.mvc.repository.watchlistRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.watchlist.Watchlist;

import java.util.List;

public interface WatchlistRepository {

    /**
     * 관심목록 저장
     * @param watchlist 관심목록 객체
     * @return 곽심목록 객체의 id
     */
    Long save(Watchlist watchlist);

    /**
     * 관심 목록 객체 가져오기
     * @param id 관심목록 객체의 id
     * @return 관심 목록 객체
     */
    Watchlist findWatchlistById(Long id);

    /**
     * 해당 post의 관심 목록 개수
     * @param post 게시글 객체
     * @return 해당 게시글의 관심 목록 개수
     */
    Long getWatchlistCount(Post post);

    /**
     * 해당 멤버가 체크하여 관심 목록에 들어가 있는 게시글
     * @param member 멤버 객체
     * @return 관심 목록에 들어가 있는 게시글 리스트
     */
    List<Post> findWatchlistByMember(Member member);
}
