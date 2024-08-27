package kr.hs.dgsw.canbusserver.domain.bus.service

import kr.hs.dgsw.canbusserver.domain.bus.persistence.BusRepository
import kr.hs.dgsw.canbusserver.domain.bus.presentation.dto.ApplyBusRequest
import kr.hs.dgsw.canbusserver.domain.bus.presentation.dto.BusResponse
import kr.hs.dgsw.canbusserver.domain.passenger.Passenger
import kr.hs.dgsw.canbusserver.domain.passenger.PassengerRepository
import kr.hs.dgsw.canbusserver.domain.user.UserRepository
import kr.hs.dgsw.canbusserver.global.security.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BusService(
    private val busRepository: BusRepository,
    private val userRepository: UserRepository,
    private val passengerRepository: PassengerRepository,
    private val securityUtil: SecurityUtil
) {

    fun getBusList(): List<BusResponse> {

        val busList = busRepository.findAll()

        return busList.map { bus ->
            BusResponse(
                busNumber = bus.busNumber,
                duration = bus.duration,
                timeRange = bus.timeRange,
                stations = bus.stations.map {
                    BusResponse.Station(
                        name = it.name,
                        time = it.time,
                    )
                }
            )
        }
    }

    fun applyBus(request: ApplyBusRequest) {

        val user = userRepository.findByIdOrNull(securityUtil.getCurrentUserId())!!

        val passenger = Passenger(
            arrivalPlace = request.arrivalPlace,
            departurePlace = request.departurePlace,
            departureTime = request.departureTime,
            user = user
        )

        passengerRepository.save(passenger)

    }

}