package com.example.praktikum5_pam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum5_pam.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel(){

    var jenilKl : String by mutableStateOf("")
        private set
    var  status : String by mutableStateOf("")
        private set
    var alamat : String by mutableStateOf("")
        private set
    var email : String by mutableStateOf("")
        private set



    private val _uiState = MutableStateFlow(DataForm())
    val uiState : StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(st: String, em: String, jk: String, almt: String){

        jenilKl = jk;
        status = st;
        alamat = almt;
        email = em;


    }

    fun setJenisK(pilihjk: String){
        _uiState.update { currentState -> currentState.copy(sex = pilihjk) }
    }
}