package com.example.nascarsim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nascarsim.ui.theme.NASCARSimTheme


import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

val RacingFont = FontFamily(Font(R.font.russo_one))



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
@Preview(showBackground = true, name = "Обычное состояние")
@Composable
fun RaceScreenPreview() {
    NASCARSimTheme {
        val car = RaceCar()
        RaceScreen(car)
    }
}

@Composable
fun RaceScreen(car: RaceCar) {

    val bgColor = if (car.isAnAccident()) Color(80, 0, 0) else Color(41, 41, 41)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgColor)
            //.padding(5.dp)

        ,
        verticalArrangement = Arrangement.SpaceBetween



    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                //.height(120.dp)
                //.background(color = Color.Black),
                    ,
            contentAlignment = Alignment.Center
        ) {
            Text("Заглушка картинки", color = Color.White)
        }

        Column(modifier = Modifier
            .weight(1.5f)

            .border(
                width = 5.dp,
                color = Color(145, 145, 145),
                shape = CutCornerShape(10.dp)
            )
            .padding(0.dp)
            .border(width = 10.dp, color = Color(63, 67, 70), shape = CutCornerShape(10.dp))
            .padding(5.dp)
            .background(color = Color.Black)){

            Row(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(5.dp)
            )
            {
                Column(
                    modifier = Modifier
                        //.fillMaxHeight()
                        .weight(2f)

                        .padding(8.dp)


                ) {
                    val engineString = if (car.isEngineStarted) "Работает" else "Не работает"

                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontFamily=RacingFont)) {
                                append("Двигатель: ")
                            }
                            withStyle(style = SpanStyle(
                                color = if(!car.isEngineStarted)Color.Red
                                else{Color.Yellow},
                                fontFamily = RacingFont
                            )) {
                                append(engineString)
                            }
                        }

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Скорость: ")}
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append("${car.carSpeed} км/ч")}
                    })

                    LinearProgressIndicator(
                        progress = { car.carSpeed / 250f },
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(8.dp),
                        color = Color.Yellow,
                        trackColor = Color.DarkGray
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Температура: ")}
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append("${car.engineTemp}°C")}
                    })
                    LinearProgressIndicator(
                        progress = { car.engineTemp / 100f },
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(8.dp)
                           // .padding(top = 4.dp),
                        ,
                        color = if (car.engineTemp < 60) {Color.Yellow
                        } else if (car.engineTemp < 80) {
                            Color(255, 165, 0) // Оранжевый
                        } else {
                            Color.Red
                        },
                        trackColor = Color.DarkGray
                    )


                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Обороты: ") }
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append("${car.engineRPM} об/мин") }
                    })

                    LinearProgressIndicator(
                        progress = { car.engineRPM / 8000f },
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(8.dp),
                        color = Color.Yellow,
                        trackColor = Color.DarkGray
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        //.background(color = Color.Blue)
                        .padding(5.dp)
                    //.fillMaxHeight()
                )
                {
                    val pitStopRec = if (car.isPitStopRecommend()) "Рекоменд." else "Не рекоменд."
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Питстоп: ")}
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append(pitStopRec) }
                    })
                    Spacer(modifier = Modifier.height(12.dp))


                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Топливо: ") }
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append("${car.fuelLevel}%") }
                    })

                    LinearProgressIndicator(
                        progress = { car.fuelLevel/ 100f },
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(8.dp),
                        color = if (car.fuelLevel > 60) {Color.Yellow
                        } else if (car.fuelLevel > 30) {Color(255, 165, 0)
                        } else {
                           Color.Red
                        },
                        trackColor = Color.DarkGray
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color= Color.White, fontFamily = RacingFont)){append("Шины: ")}
                        withStyle(style = SpanStyle(color= Color.Yellow, fontFamily = RacingFont)){append("${car.tireWear}%") }
                    })
                    LinearProgressIndicator(
                        progress = { car.tireWear/ 100f },
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(8.dp),
                        color = if (car.tireWear > 60) {Color.Yellow
                        } else if (car.tireWear > 30) {Color(255, 165, 0)
                        } else {
                            Color.Red
                        },
                        trackColor = Color.DarkGray
                    )
                }
            }
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border( width = 5.dp, color = Color(63, 67, 70), shape = CutCornerShape(10.dp))
                .padding(10.dp)

                )
            {
               Box( contentAlignment = Alignment.Center,
                   modifier = Modifier.weight(1f)
               ) {
                   if(car.fuelLevel<=60)
                    Image(
                        painter = painterResource(id = R.drawable.fuel_icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)

                    )
                }

                Box( contentAlignment = Alignment.Center,
                     modifier = Modifier.weight(1f)
                ) {
                    if(car.engineTemp>59)
                     Image(
                        painter = painterResource(id = R.drawable.temp_icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)

                    )
                }
                Box( contentAlignment = Alignment.Center,
                     modifier = Modifier.weight(1f)
                ) {
                    if(car.tireWear <60)
                    Image(
                        painter = painterResource(id = R.drawable.tire_icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)

                    )
                }
                Box( contentAlignment = Alignment.Center,
                     modifier = Modifier.weight(1f)
                ) {
                    /*Image(
                        painter = painterResource(id = R.drawable.fuel_icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)

                    )*/
                }

                if (car.isAnAccident()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Yellow)
                            .padding(10.dp)
                            ,

                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "АВАРИЯ! МОТОР ПЕРЕГРЕТ / ЛОПНУЛИ ШИНЫ",
                            fontSize = 20.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }
        }



        Row(modifier= Modifier.weight(1.5f)){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()

                    //.background(color = Color.Blue)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(

                    onClick = { car.startStopEngine() },

                    modifier = Modifier
                        //.weight(2f)
                        .size(160.dp)
                        .border(width = 10.dp, color = Color(230, 234, 237), shape = CircleShape)
                        .padding(10.dp)
                        .border(width = 10.dp, color = Color(63, 67, 70), shape = CircleShape)
                    ,shape = CircleShape

                ) {
                    Text(" Завести \nЗаглушить", textAlign = TextAlign.Center, fontWeight= FontWeight.W900)
                }

                Spacer(modifier = Modifier.height(8.dp))


                Button(
                    shape = CutCornerShape(5.dp),

                    onClick = { car.toPitStop() },
                    enabled = car.carSpeed < 20,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) {
                    Text("Пит-стоп")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    shape = CutCornerShape(5.dp),
                    onClick = { car.resetCar() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text("Сброс")
                }
            }



            Column(modifier = Modifier
                .weight(1f)
                .padding(5.dp)
            )
            {   Button(
                shape = CutCornerShape(5.dp),
                onClick = { car.accelerate() },
                enabled = car.isEngineStarted && !car.isAnAccident(),
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            ) {
                Text("Газ")
            }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    shape = CutCornerShape(5.dp),
                    onClick = { car.brake() },
                    enabled = car.carSpeed > 0,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()

                ) {
                    Text("Тормоз")
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
    }
}