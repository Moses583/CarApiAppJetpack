package com.ravemaster.carapiapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravemaster.carapiapp.data.remote.models.ApiResponse
import com.ravemaster.carapiapp.domain.repository.CarApiRepository
import com.ravemaster.carapiapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val carApiRepository: CarApiRepository
): ViewModel() {

    var state by mutableStateOf(MyStates())
        private set


    fun loadCarInfo(vin: String){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            carApiRepository.getCarFromVin(vin).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        state = state.copy(
                            apiResponse = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            apiResponse = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }


}