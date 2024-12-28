package br.com.lno.android_mvvm_compose.domain.usecase.base

interface BaseUseCase<T> {
    suspend fun execute(): T
}