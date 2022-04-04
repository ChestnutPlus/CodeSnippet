package x.chestnut.code.snippet.test

import io.mockk.*
import org.junit.Test

class CarTest {

    @Test
    fun `should print when recordTelemetry`() {
        //GIVEN
        val car = mockk<Car>()
        val slot = slot<Double>()
        every {
            car.recordTelemetry(speed = capture(slot))
        } answers {
            println(slot.captured)
        }
        //WHEN
        car.recordTelemetry(15.0)
        //THEN
        verify {
            car.recordTelemetry(speed = 15.0)
        }
    }

    @Test
    fun `should return no when sumVoid`() {
        val car = spyk<Car>()
        every { car.carName = any() } propertyType String::class answers {
            println("第一个参数是：$fieldValue")
            fieldValue = "abc"
        }
        car.carName = "cde"
        println("name: ${car.carName}")
    }
}