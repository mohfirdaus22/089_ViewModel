package com.example.praktikum5_pam

import android.content.pm.ChangedPackages
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum5_pam.Data.DataForm
import com.example.praktikum5_pam.Data.DataSource.jenis
import com.example.praktikum5_pam.Data.DataSource.status
import com.example.praktikum5_pam.ui.theme.Praktikum5_PAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktikum5_PAMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) ->Unit ={}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Row( modifier = Modifier.padding(16.dp)) {
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically

            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun SelectST(
    options: List<String>,
    onSelectionChanged: (String) ->Unit ={}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Row( modifier = Modifier.padding(16.dp)) {
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically

            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}


@Composable
fun TampilLayout( modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),// Mengatur jarak antara elemen-elemen dalam kolom secara vertikal
            horizontalAlignment = Alignment.CenterHorizontally,  // Menengahkan elemen-elemen dalam kolom secara horizontal
            modifier = Modifier.padding(20.dp)// Menambahkan margin sebesar 20dp ke dalam kolom
        ){
          TampilForm()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){

    var textNama by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var textEml by remember { mutableStateOf("") }
    var textAlmt by remember { mutableStateOf("") }


    val context = LocalContext.current // untuk mendapatkan akses ke Context yang digunakan dalam konteks lokal komponen.
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState;

    Row (verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = null, modifier = Modifier.size(10.dp))

        Spacer(modifier = Modifier.padding(3.dp))

        Text(text =  "Register",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold)

    }
    Text(text = "Create Your Account",
        fontSize = 25.sp, color = Color.Gray, textAlign = TextAlign.Center
    )

    OutlinedTextField(
        value = textNama,
        shape = MaterialTheme.shapes.large ,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Username")},
        onValueChange ={
            textNama = it
        }
    )
    OutlinedTextField(
        value = textTlp,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large ,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telepon")},
        onValueChange ={
            textTlp= it
        }
    )
    OutlinedTextField(
        value = textEml,
        shape = MaterialTheme.shapes.large ,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email")},
        onValueChange ={
            textEml = it
        }
    )
    Text(text = "Jenis Kelamin :",modifier = Modifier
        .padding(horizontal = 5.dp, vertical = 5.dp))

    SelectJK(
        options = jenis.map{id ->context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setJenisK(it)})

    Text(text = "Status:",modifier = Modifier
        .padding(horizontal = 5.dp, vertical = 5.dp))

    SelectST(
        options = status.map{id ->context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setJenisK(it)})

    OutlinedTextField(
        value = textAlmt,
        shape = MaterialTheme.shapes.large ,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat")},
        onValueChange ={
            textAlmt = it
        }
    )

    Button(
        onClick = { cobaViewModel.insertData(textTlp,textEml, dataForm.sex ,textAlmt, ) }
    ) {
        Text(
            text = stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    TextHasil(
        jenisnnya = cobaViewModel.jenilKl,
        statusnya = cobaViewModel.status,
        alamatnya = cobaViewModel.alamat,
        emailnya = cobaViewModel.email,
    )

}


@Composable
fun TextHasil( statusnya: String, alamatnya: String, jenisnnya: String, emailnya: String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Jenis Kelamin :" + alamatnya ,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Status : " + jenisnnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )


    }
}








@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum5_PAMTheme {
        TampilLayout()
    }
}