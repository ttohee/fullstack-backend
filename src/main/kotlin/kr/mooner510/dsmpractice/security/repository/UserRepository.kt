package kr.mooner510.dsmpractice.security.repository

import kr.mooner510.dsmpractice.security.data.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {


    fun existsByName(name: String): Boolean


    fun findByName(name: String): Optional<User>
}
