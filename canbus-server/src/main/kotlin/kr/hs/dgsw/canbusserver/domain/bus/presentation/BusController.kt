package kr.hs.dgsw.canbusserver.domain.bus.presentation

import kr.hs.dgsw.canbusserver.domain.bus.presentation.dto.ApplyBusRequest
import kr.hs.dgsw.canbusserver.domain.bus.presentation.dto.BusResponse
import kr.hs.dgsw.canbusserver.domain.bus.service.BusService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bus")
class BusController(
    private val busService: BusService
) {

    @GetMapping
    fun getBusList(): List<BusResponse> {
        return busService.getBusList()
    }

    @PostMapping("/apply")
    fun applyBus(@RequestBody request: ApplyBusRequest) {
        busService.applyBus(request)
    }

}