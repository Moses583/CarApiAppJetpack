package com.ravemaster.carapiapp.domain.repository

import com.ravemaster.carapiapp.data.remote.models.ApiResponse
import com.ravemaster.carapiapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CarApiRepository {

suspend fun getCarFromVin(vin: String): Flow<Resource<ApiResponse>>

}