package x.chestnut.code.snippet.test

class Car {
    var carName = "BenChi"
    private val userAge = 16
    fun recordTelemetry(speed: Double) {

    }
    fun sumUnit(a: Int, b: Int): Unit {
        println(a+b)
    }
    fun sumVoid(a: Int, b: Int) {
        println(a+b)
    }
    fun hello() {
        print("hi, my age is $userAge")
    }
}