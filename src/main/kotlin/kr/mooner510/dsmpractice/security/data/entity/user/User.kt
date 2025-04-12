package kr.mooner510.dsmpractice.security.data.entity.user

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var password: String,
)


//어노테이션을 많이 쓴다. 필요한건 외워두는게 좋을듯 하다.