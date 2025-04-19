package kr.mooner510.dsmpractice.security.data.entity.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    private var password: String, // var 로 선언시 getter,setter 를 자동 생성. private 사용시 자동생성X
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList()
    }

    @Transient
    override fun getPassword(): String { // 두 getter(자동생성, 직접정의)가 충돌
        return password
    }

    override fun getUsername(): String {
        return name
    }
}


//어노테이션을 많이 쓴다. 필요한건 외워두는게 좋을듯 하다.