package kr.hs.dgsw.canbusserver.domain.bus.presentation.dto

class BusResponse(

    val duration: String,

    val timeRange: String,

    val busNumber: String,

    val stations: List<Station>,

    ) {

    class Station(
        val name: String,
        val time: String,
    )

}