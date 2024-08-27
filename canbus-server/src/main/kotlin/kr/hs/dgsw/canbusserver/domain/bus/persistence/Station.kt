package kr.hs.dgsw.canbusserver.domain.bus.persistence

import jakarta.persistence.*

@Entity
@Table(name = "station")
class Station(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val time: String,

)