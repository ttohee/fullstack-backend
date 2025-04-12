package kr.mooner510.dsmpractice.domain.createPost.data.request

data class PostRequest (
    val author: String,
    val title: String,
    val content: String,
)