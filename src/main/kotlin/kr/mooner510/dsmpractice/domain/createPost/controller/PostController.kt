package kr.mooner510.dsmpractice.domain.createPost.controller

import kr.mooner510.dsmpractice.domain.createPost.data.entity.post.Post
import kr.mooner510.dsmpractice.domain.createPost.data.request.PostRequest
import kr.mooner510.dsmpractice.domain.createPost.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @PostMapping("/create")
    fun createPost(@RequestBody req: PostRequest) {
        return postService.createPost(req)
    }

    @GetMapping("/read")
    fun readAllPost(): List<Post> {
        return postService.getAllPosts()
    }

    @GetMapping("/read/{id}")
    fun readOnePost(@PathVariable id: Long): Optional<Post> {
        return postService.getOnePost(id)
    }

    @PutMapping("/update/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody req: PostRequest): List<Post> {
        return postService.updatePost(id, req)
    }

    @DeleteMapping("/delete/{id}")
    fun deletePost(@PathVariable id: Long): List<Post> {
        return postService.deletePost(id)
    }
}