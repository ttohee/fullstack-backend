package kr.mooner510.dsmpractice.domain.createPost.repository

import kr.mooner510.dsmpractice.domain.createPost.data.entity.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
    fun id(id: Long): MutableList<Post>
}