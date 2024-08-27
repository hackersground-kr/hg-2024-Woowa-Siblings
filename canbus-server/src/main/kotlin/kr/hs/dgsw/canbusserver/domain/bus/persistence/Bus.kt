package kr.hs.dgsw.canbusserver.domain.bus.persistence

import jakarta.persistence.*

@Entity
@Table(name = "bus")
class Bus (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val duration: String,

    val timeRange: String,

    val busNumber: String,

    @OneToMany
    @JoinColumn(name = "bus_id")
    val stations: List<Station>

)