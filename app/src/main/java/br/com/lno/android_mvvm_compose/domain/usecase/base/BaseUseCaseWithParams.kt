package br.com.lno.android_mvvm_compose.domain.usecase.base

interface BaseUseCaseWithParam<P, T> {
    suspend fun execute(param: P): T
}