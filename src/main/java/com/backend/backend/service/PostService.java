package com.backend.backend.service;

import com.backend.backend.controller.post.Dto.PostModifyRequest;
import com.backend.backend.controller.post.Dto.PostWriteRequest;
import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.exception.NotExistException;
import com.backend.backend.exception.PermissionDeniedException;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import com.backend.backend.repository.postRepository.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * post객체 전달시 저장
     * @param postWriteRequest
     * @return
     */

    @Transactional
    public Long writePost(PostWriteRequest postWriteRequest){
        Post post = Post.builder()
                .writer(getMember(postWriteRequest))
                .postName(postWriteRequest.getPostName())
                .content(postWriteRequest.getContent())
                .build();

        return postRepository.save(post);
    }

    private Member getMember(PostWriteRequest postWriteRequest) {
        Member member = memberRepository.findMemberByNickname(postWriteRequest.getWriterNickname());
        if(member==null){
            throw new NotExistException("존재하지 않는 회원 입니다");
        }
        return member;
    }

    /**
     * post를 식별 가능한 방법이 id 뿐이기에 id로 검색하여 Post반환
     * @param id
     * @return
     */
    public Post readPost(Long id){
        return postRepository.findPostById(id);
    }

    /**
     * 조건에 맞는 모든 post를 찾아서 반환 만약 postName이 null이면 전체 반환
     * @param postSearch
     * @return
     */
    public List<Post> searchPost(PostSearch postSearch){
        System.out.println(postSearch.getPostName());
        System.out.println("++++++++++++++++++++++++++++++++++");
        return postRepository.searchPost(postSearch);
    }

    /**
     * post를 업데이트
     * @param postModifyRequest
     * @return
     */
    @Transactional
    public Long modifyPost(PostModifyRequest postModifyRequest){
        Post post = postRepository.findPostById(postModifyRequest.getPostId());
        Member modifier = memberRepository.findMemberByNickname(postModifyRequest.getModifierNickname());

        if(!(post.getWriter().getId().equals(modifier.getId()))){
            throw new PermissionDeniedException("해당 회원은 이 글을 수정할 권한이 없습니다");
        }

        if(postModifyRequest.getPostName()!=null){
            post.setPostName(postModifyRequest.getPostName());
        }
        if(postModifyRequest.getContent()!=null) {
            post.setContent(postModifyRequest.getContent());
        }
        return post.getId();
    }

    /**
     * post를 식별가능한 방법이 id 뿐이기에 id로 삭제
     * @param id
     */
    @Transactional
    public void deletePost(Long id){
        System.out.println(id);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

        postRepository.deletePostById(id);

    }

    /**
     * jwt 토큰를 decode하고 payload부분을 json 파싱해서 뽑아낸 nickname으로 유저가 작성한 게시글 조회
     * @param nickname
     */
    @Transactional
    public List<Post> getAllboard(String nickname)
    {
        List<Post> boardlist;
        Member member=memberRepository.findMemberByNickname(nickname);
        boardlist=postRepository.findPostsByMember(member);
        return boardlist;
    }


}
