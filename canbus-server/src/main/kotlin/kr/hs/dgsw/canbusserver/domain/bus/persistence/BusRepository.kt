package kr.hs.dgsw.canbusserver.domain.bus.persistence

import org.springframework.data.repository.CrudRepository

interface BusRepository : CrudRepository<Bus, Long> {
}