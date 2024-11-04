package com.ravemaster.carapiapp.data.remote

import com.ravemaster.carapiapp.data.remote.models.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CarApi {
    @GET("vin/{vin}")
    @Headers("x-rapidapi-key: 7e3d2f10bdmsh70e6fefa71835adp16c240jsnb41f1b9c1073", "x-rapidapi-host: car-api2.p.rapidapi.com")
    suspend fun getCarFromVIN(
        @Path("vin") vin: String
    ): ApiResponse

    companion object{
        const val BASE_URL = "https://car-api2.p.rapidapi.com/api/"
    }

}