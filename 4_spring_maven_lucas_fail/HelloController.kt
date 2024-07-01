package myapp

import kotlin.system.measureNanoTime
import org.springframework.web.bind.annotation.*

@RestController
class HelloController {

    @PostMapping("/calculate")
    fun calculateLucas(@RequestBody request: LucasRequest): LucasResponse {
        val n = request.n
        val (result, time) =
                measureNanoTime { calculateLucasNumber(n) }.let { time ->
                    calculateLucasNumber(n) to time
                }
        return LucasResponse(result, time)
    }

    fun calculateLucasNumber(n: Int): Long {
        if (n == 0) return 2
        if (n == 1) return 1
        var a = 2L
        var b = 1L
        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
        }
        return b
    }
}

data class LucasRequest(val n: Int)

data class LucasResponse(val result: Long, val process_time: Long)
