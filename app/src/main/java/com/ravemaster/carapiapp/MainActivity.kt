package com.ravemaster.carapiapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravemaster.carapiapp.presentation.MyStates
import com.ravemaster.carapiapp.presentation.MyViewModel
import com.ravemaster.carapiapp.ui.theme.CarApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarApiAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarInput(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun CarInput(modifier: Modifier = Modifier) {
        var textState by remember {
            mutableStateOf("")
        }
        val  state = viewModel.state
        val context = LocalContext.current
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = textState,
                onValueChange = { textState = it },
                label = { Text("Enter VIN number") })
            Button(
                modifier = Modifier
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Black
                ),
                onClick = {
                    if (textState.isNotBlank()) {
                        viewModel.loadCarInfo(textState)
                    } else {
                        Toast.makeText(context,"Text field is empty", Toast.LENGTH_LONG).show()
                    }
            }) {
                Text("Search for car")
            }
            if (state.isLoading){
                CircularProgressIndicator()
            }else{
                CarContent(state)
            }
        }
    }

    @Composable
    fun CarContent(state: MyStates) {
        state.apiResponse?.let {
            Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ){
                 Text(
                     "Make: ${it.make}",
                     color =  Color.Blue,
                     fontSize = 20.sp,
                 )
                Text(
                    "Model: ${it.model}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Trim: ${it.trim}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Year: ${it.year}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Price: ${it.specs.base_price}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Drive type: ${it.specs.drive_type}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Doors: ${it.specs.doors}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "Top speed: ${it.specs.top_speed_mph}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text("Displacement: ${it.specs.displacement_cc}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
                Text(
                    "ABS: ${it.specs.anti_lock_braking_system_abs}",
                    color =  Color.Blue,
                    fontSize = 20.sp,
                )
            }
        } ?: state.error?.let {
            Text("Error: $it", color = Color.Red)
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun CarContentPreview(){
        CarInput()
    }
}

