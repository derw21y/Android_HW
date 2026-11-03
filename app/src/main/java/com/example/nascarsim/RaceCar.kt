package com.example.nascarsim

class RaceCar
{
    var fuelLevel : Int = 100
    var tireWear : Int = 100
    var isEngineStarted : Boolean = false
    var engineTemp : Int = 20
    var carSpeed : Int  = 0
    var isOnPitStop : Boolean = false

    fun startStopEngine()
    {
        if(!isEngineStarted)
        {
            if(fuelLevel > 0 && engineTemp <100)
            {
                 isEngineStarted = true
            }
        }
        else{isEngineStarted = false}
    }

    fun toPitStop(){
        if(carSpeed <20){
            isOnPitStop= true
            carSpeed =0
        }
    }

    fun leavePitStop(){
        isOnPitStop = false
        carSpeed=20
    }

    fun pitStop(){
        if(isOnPitStop){
            fuelLevel = 100
            tireWear =100
            engineTemp = 20


        }

    }

    fun accelerate(){
        if(isEngineStarted && fuelLevel > 0 && !isOnPitStop  && carSpeed <250) {
            carSpeed = (carSpeed +10).coerceAtMost(250)
            fuelLevel = (fuelLevel -5).coerceAtLeast(0)
            tireWear = (tireWear- 5).coerceAtLeast(0)
            engineTemp +=15
        }
    }
    fun brake(){
        if(!isOnPitStop && carSpeed > 0){
            carSpeed =carSpeed - 5
            engineTemp = (engineTemp- 3).coerceAtLeast(0)
        }
    }
}