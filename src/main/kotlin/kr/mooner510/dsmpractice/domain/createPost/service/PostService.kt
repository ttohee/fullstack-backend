package kr.mooner510.dsmpractice.domain.createPost.service

import jakarta.transaction.Transactional
import kr.mooner510.dsmpractice.domain.createPost.data.entity.post.Post
import kr.mooner510.dsmpractice.domain.createPost.data.request.PostRequest
import kr.mooner510.dsmpractice.domain.createPost.repository.PostRepository
import kr.mooner510.dsmpractice.global.error.ErrorCode
import kr.mooner510.dsmpractice.global.error.data.GlobalError
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(req: PostRequest) {
        if (req.title.isBlank() && req.content.isBlank()) {
            throw GlobalError(ErrorCode.BLANK_CONTENT)
        }

        postRepository.save(Post(0, req.author, req.title, req.content, LocalDateTime.now()))
    }

    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    @Transactional
    fun updatePost(id: Long, request: PostRequest): List<Post> {
        val post = postRepository.findById(id).get()
        post.title = request.title
        post.content = request.content
        post.date = LocalDateTime.now()

        return postRepository.findAll()
    }

    fun deletePost(id: Long): List<Post> {
        postRepository.deleteById(id)
        return postRepository.findAll()
    }
}