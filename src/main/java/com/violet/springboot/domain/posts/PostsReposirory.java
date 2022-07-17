package com.violet.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsReposirory extends JpaRepository<Posts, Long> {

}
