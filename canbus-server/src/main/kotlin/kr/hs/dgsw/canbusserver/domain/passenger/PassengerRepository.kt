package kr.hs.dgsw.canbusserver.domain.passenger

import org.springframework.data.repository.CrudRepository

interface PassengerRepository : CrudRepository<Passenger, Long> {
}