package mkw22

import kotlin.system.measureTimeMillis
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LucasController {

    @PostMapping("/check")
    fun checkLucasNumber(@RequestBody request: NumberRequest): NumberResponse {
        val number = request.value
        var duration: Long = 0
        var result = false

        duration = measureTimeMillis { result = isLucasNumber(number) }

        return NumberResponse(result, duration / 1000.0)
    }

    private fun isLucasNumber(n: Int): Boolean {
        if (n == 2 || n == 1) return true
        var a = 2
        var b = 1
        while (b < n) {
            val temp = a + b
            a = b
            b = temp
        }
        return b == n
    }
}

data class NumberRequest(val value: Int)

data class NumberResponse(val result: Boolean, val duration: Double)
