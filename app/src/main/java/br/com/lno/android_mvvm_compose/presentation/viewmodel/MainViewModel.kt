package br.com.lno.android_mvvm_compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.android_mvvm_compose.domain.usecase.GetContinentsUseCase
import br.com.lno.android_mvvm_compose.presentation.continents.ContinentsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getContinentsUseCase: GetContinentsUseCase) :
    ViewModel() {

    private val _result = MutableStateFlow<ContinentsViewState>(ContinentsViewState.Loading)
    val result = _result.asStateFlow()

    init {
        getContinents()
    }

    fun getContinents() {
        viewModelScope.launch {
            getContinentsUseCase.execute().collect {
                _result.emit(it)
            }
        }
    }
}