package com.ravemaster.carapiapp.data.repository

import androidx.compose.runtime.collectAsState
import com.ravemaster.carapiapp.data.remote.CarApi
import com.ravemaster.carapiapp.data.remote.models.ApiResponse
import com.ravemaster.carapiapp.domain.repository.CarApiRepository
import com.ravemaster.carapiapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CarApiRepositoryImpl @Inject constructor(
    private val carApi: CarApi
): CarApiRepository{
    override suspend fun getCarFromVin(vin: String): Flow<Resource<ApiResponse>> {

        return flow {
            emit(Resource.Loading(true))

            val carFromApi = try {
                carApi.getCarFromVIN(vin)
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(" IOException"))
                return@flow
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("HttpException ${e.code()}"))
                return@flow
            } catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error("General exception"))
                return@flow
            }
            emit(Resource.Success(carFromApi))

        }

    }

}