package com.violet.springboot.service.posts;

import com.violet.springboot.domain.posts.Posts;
import com.violet.springboot.domain.posts.PostsReposirory;
import com.violet.springboot.web.dto.PostsResponseDto;
import com.violet.springboot.web.dto.PostsSaveRequestDto;
import com.violet.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsReposirory postsReposirory;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsReposirory.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsReposirory.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsReposirory.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
