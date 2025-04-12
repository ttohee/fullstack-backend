package kr.mooner510.dsmpractice.domain.createPost.controller

import jakarta.annotation.PostConstruct
import kr.mooner510.dsmpractice.domain.createPost.data.request.PostRequest
import kr.mooner510.dsmpractice.domain.createPost.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @PostConstruct
    fun init() {
        println("✅ PostController 생성됨")
    }

    @PostMapping("/create")
    fun createPost(@RequestBody req: PostRequest) {
        println("컨트롤러 들어옴")
        return postService.createPost(req)
    }
}