package kr.hs.dgsw.canbusserver.domain.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun existsByIdentifier(userId: String): Boolean

    fun findByIdentifier(userId: String): User?

}