package com.example.taskn21.presentation.screen.clothes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskn21.data.remote.common.Resource
import com.example.taskn21.domain.use_case.AddClothesToDataBase
import com.example.taskn21.domain.use_case.GetClothesFromApiUseCase
import com.example.taskn21.domain.use_case.GetClothesFromDataBaseUseCase
import com.example.taskn21.presentation.event.ClothesEvent
import com.example.taskn21.presentation.model.Clothes
import com.example.taskn21.presentation.state.ClothesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val getClothesFromApiUseCase: GetClothesFromApiUseCase,
    getClothesFromDataBaseUseCase: GetClothesFromDataBaseUseCase,
    private val addClothesUseCase: AddClothesToDataBase
) : ViewModel() {

    private val _usersStateFlow = MutableStateFlow(ClothesState())
    val usersStateFlow = _usersStateFlow.asStateFlow()

    private val _clothesFlow = getClothesFromDataBaseUseCase()

    init {
        getUsers()
    }

    fun onEvent(event: ClothesEvent) {
        when(event) {
            is ClothesEvent.AddAllClotheToDataBaseEvent -> addAllClothes()
            is ClothesEvent.SelectFavouriteEvent -> selectFavourite(selectedClothes = event.item)
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getClothesFromApiUseCase().collect {
                when(it) {
                    is Resource.Loading -> _usersStateFlow.update { currentState -> currentState.copy(isLoading = it.loading) }

                    is Resource.Success -> {
                        _usersStateFlow.update { currentState -> currentState.copy(clothes = it.response) }
                        addAllClothes()
                    }

                    is Resource.Error -> {
                        if (it.throwable is IOException){
                            _clothesFlow.collect {clothes ->
                                if (clothes.isNotEmpty())
                                    _usersStateFlow.update { currentState -> currentState.copy(clothes = clothes, isLoading = false) }
                                else
                                    _usersStateFlow.update { clothesState -> clothesState.copy(errorMessage = "No Items Found",  isLoading = false) }

                            }
                        } else {
                            _usersStateFlow.update { currentState -> currentState.copy(errorMessage = it.message) }
                        }
                    }
                }
            }
        }
    }

    private fun addAllClothes() {
        viewModelScope.launch {
            addClothesUseCase(_usersStateFlow.value.clothes!!)
        }
    }

    private fun addSingleClothes(selectedClothes: Clothes) {
        viewModelScope.launch {
            addClothesUseCase(listOf(selectedClothes))
        }
    }


    private fun selectFavourite(selectedClothes: Clothes) {
        viewModelScope.launch {
            val modifiedClothesList = _usersStateFlow.value.clothes?.map { clothes ->
                if (clothes.id == selectedClothes.id) {
                    clothes.copy(favorite = !selectedClothes.favorite)
                } else {
                    clothes
                }
            }
            _usersStateFlow.update { currentState -> currentState.copy(clothes = modifiedClothesList) }
            addSingleClothes(selectedClothes.copy(favorite = !selectedClothes.favorite))
        }
    }
}