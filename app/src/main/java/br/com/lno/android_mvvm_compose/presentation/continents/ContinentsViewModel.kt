package br.com.lno.android_mvvm_compose.presentation.continents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.android_mvvm_compose.domain.usecase.GetContinentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(private val getContinentsUseCase: GetContinentsUseCase) :
    ViewModel() {

    private val _result = MutableLiveData<ContinentsViewState>(ContinentsViewState.Loading)
    val result: LiveData<ContinentsViewState> = _result

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