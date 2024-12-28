package br.com.lno.android_mvvm_compose.presentation.continents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.android_mvvm_compose.domain.usecase.GetContinentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(private val getContinentsUseCase: GetContinentsUseCase) :
    ViewModel() {

    private val _result = MutableStateFlow<ContinentsViewState>(ContinentsViewState.Loading)
    val result = _result.asStateFlow()

    init {
        getContinents()
    }

    fun getContinents() {
        _result.value = ContinentsViewState.Loading
        viewModelScope.launch {
            _result.value = try {
                ContinentsViewState.Success(data = getContinentsUseCase.execute())
            } catch (e: Exception) {
                ContinentsViewState.Failure
            }
        }
    }
}