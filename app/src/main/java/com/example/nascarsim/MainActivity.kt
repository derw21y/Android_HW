package com.example.nascarsim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nascarsim.ui.theme.NASCARSimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NASCARSimTheme {
                val car = remember { RaceCar() }
                RaceScreen(car)
            }
        }
    }
}

@Composable
fun RaceScreen(car: RaceCar) {



    Column(modifier = Modifier.fillMaxSize()
        , verticalArrangement = Arrangement.SpaceBetween
    ) {
        //Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxWidth().background(color = Color.Black))
        {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Заглушка картинки",color = Color.White)

        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Red)
                .padding(16.dp)
        ) {
           // Text("🏎️ NASCAR Simulator", fontSize = 24.sp, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            val engineString = if (car.isEngineStarted) "Работает" else "Не работает"
            Text("Двигатель: $engineString", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))



            Text("Скорость: ${car.carSpeed} км/ч", color = Color.White)
            Text("Топливо: ${car.fuelLevel}%", color = Color.White)
            Text("Шины: ${car.tireWear}%", color = Color.White)
            Text("Температура: ${car.engineTemp}°C", color = Color.White)
        }

        Column (modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .height(200.dp)
        ){

            if (car.isAnAccident()) {
                Text("🔥 АВАРИЯ! МОТОР ПЕРЕГРЕТ ИЛИ ШИНЫ ЛОПНУЛИ", fontSize = 30.sp,
                    modifier  =Modifier.fillMaxSize())
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { car.startStopEngine() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !car.isEngineOverheated()
            ) {
                Text("Завести/Заглушить")

            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { car.accelerate() },

                enabled = car.isEngineStarted && !car.isAnAccident(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Газ")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { car.brake() },

                //enabled = car.carSpeed > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Тормоз")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { car.toPitStop() },

                enabled = car.carSpeed < 20,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Пит-стоп")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { car.resetCar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сброс")
            }
        }
    }
}