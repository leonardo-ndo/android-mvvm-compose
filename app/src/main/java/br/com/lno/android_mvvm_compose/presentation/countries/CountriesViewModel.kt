package br.com.lno.android_mvvm_compose.presentation.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.android_mvvm_compose.domain.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getCountriesUseCase: GetCountriesUseCase) :
    ViewModel() {

    private var _countries = MutableLiveData<CountriesViewState>(CountriesViewState.Loading)
    val countries: LiveData<CountriesViewState> = _countries

    fun getCountries(continent: String) {
        _countries.value = CountriesViewState.Loading
        viewModelScope.launch {
            _countries.value = try {
                CountriesViewState.Success(data = getCountriesUseCase.execute(continent))
            } catch (e: Exception) {
                e.printStackTrace()
                CountriesViewState.Failure
            }
        }
    }
}