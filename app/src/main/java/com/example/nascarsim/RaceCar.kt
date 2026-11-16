package com.example.nascarsim

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



    const val maxSpeed = 250
const val maxRPM = 8000
const val idleRPM = 1000
const val maxTemp = 100
const val pitRecTemp = 80
const val pitRecTire = 30
const val pitRecFuel = 60
const val maxFuel = 100
const val maxTire = 100
//const val

//const val

class RaceCar
{
    var fuelLevel by mutableIntStateOf(100)
    var tireWear by mutableIntStateOf(100)
    var isEngineStarted by mutableStateOf(false)
    var engineTemp by mutableIntStateOf(20)

    var engineRPM by mutableIntStateOf(0)
    var carSpeed by mutableIntStateOf(0)
    var isOnPitStop by mutableStateOf(false)

    fun startStopEngine()
    {
        if(!isEngineStarted)
        {
            if(fuelLevel > 0 && !isEngineOverheated())
            {
                 isEngineStarted = true
                engineRPM = idleRPM
            }
        }
        else{isEngineStarted = false
            engineRPM =0}
    }

    fun isTiresBurst(): Boolean = tireWear ==0

    fun isEngineOverheated(): Boolean = engineTemp >= maxTemp
    fun isAnAccident(): Boolean= isEngineOverheated()  || isTiresBurst()

    fun isPitStopRecommend(): Boolean= engineTemp>pitRecTemp  || tireWear <pitRecTire || fuelLevel < pitRecFuel

    fun toPitStop(){
        if(carSpeed <=20 ){
            isOnPitStop= true
            carSpeed =0
            pitStop()
            leavePitStop()
        }
    }

    fun leavePitStop(){
        isOnPitStop = false
        isEngineStarted = true
        carSpeed=20
        engineRPM = idleRPM +(carSpeed*20)

    }

    fun pitStop(){
        if(isOnPitStop){
            fuelLevel = maxFuel
            tireWear = maxTire
            engineTemp = 20
            engineRPM = idleRPM


        }

    }




    fun accelerate(){
        if(isEngineStarted && fuelLevel > 0 && !isOnPitStop  && carSpeed <maxSpeed && !isAnAccident()) {
            carSpeed = (carSpeed +10).coerceAtMost(maxSpeed)
            fuelLevel = (fuelLevel -5).coerceAtLeast(0)
            tireWear = (tireWear- 3).coerceAtLeast(0)
            engineTemp +=7
            engineRPM = (idleRPM+carSpeed*20).coerceAtMost(maxRPM)
            if(isAnAccident()){
                isEngineStarted = false
                carSpeed = 0
                engineRPM = 0
            }
            if (fuelLevel == 0) {
                isEngineStarted = false // Двигатель выключается
                engineRPM = 0}

            }

    }
    fun brake(){
        if(!isOnPitStop && carSpeed > 0){
            carSpeed =(carSpeed - 5).coerceAtLeast(0)
            engineTemp = (engineTemp- 3).coerceAtLeast(0)
            tireWear = (tireWear-1).coerceAtLeast(0)
            engineRPM = if(isEngineStarted){
                (idleRPM + carSpeed*20).coerceAtMost(maxRPM)
            }
            else{0}
        }
    }

    fun resetCar(){
        fuelLevel = maxFuel
         tireWear = maxTire
         isEngineStarted = false
        engineTemp  = 20
         carSpeed  = 0
         isOnPitStop= false
        engineRPM = 0

    }
}