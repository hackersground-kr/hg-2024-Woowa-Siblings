package kr.hs.dgsw.canbusserver.domain.passenger

import jakarta.persistence.*
import kr.hs.dgsw.canbusserver.domain.user.User

@Entity
@Table(name = "passenger")
class Passenger (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val arrival: String,

    val departure: String,

    val departureTime: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

)