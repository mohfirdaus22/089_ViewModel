package com.example.praktikum5_pam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum5_pam.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CobaViewModel : ViewModel(){
    var  namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenilKl : String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataForm())
    val uiState : StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String, jk: String){

    }
}