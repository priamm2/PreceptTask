package com.example.precepttask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.precepttask.model.PreceptFactor
import com.example.precepttask.repository.PreceptFactorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreceptFactorViewModel @Inject constructor(
    private val preceptFactorRepository: PreceptFactorRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _filteredPreceptFactors: MutableStateFlow<List<PreceptFactor>> = MutableStateFlow(emptyList())
    val filteredPreceptFactors: StateFlow<List<PreceptFactor>> = _filteredPreceptFactors.asStateFlow()


    init {
        refreshData()
        viewModelScope.launch {
            searchText
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    _isLoading.emit(true)
                    try {
                        preceptFactorRepository.searchPreceptFactors(query)
                    } catch (_: Exception) {
                        emptyFlow()
                    } finally {
                        _isLoading.emit(false)
                    }
                }
                .collect { factors ->
                    _filteredPreceptFactors.value = factors
                }
        }
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun refreshData() {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                preceptFactorRepository.refreshData()
            } catch (e: Exception) {
                println("Error during refresh: ${e.message}")
                throw e
            } finally {
                _isLoading.emit(false)
            }
        }
    }

}