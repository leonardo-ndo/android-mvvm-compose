package br.com.lno.android_mvvm_compose.domain.repository

import br.com.lno.android_mvvm_compose.domain.model.Continent

interface ContinentsRepository {
    suspend fun getContinents(): List<Continent>
}