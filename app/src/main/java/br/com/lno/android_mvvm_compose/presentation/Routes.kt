package br.com.lno.android_mvvm_compose.presentation

import kotlinx.serialization.Serializable

@Serializable
object Continents

@Serializable
data class Countries(val continentCode: String, val continentName: String)