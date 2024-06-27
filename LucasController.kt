package myapp

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

@RestController
class LucasController {

    @PostMapping("/calculate")
    fun calculateLucas(@RequestBody request: Map<String, Int>): Map<String, Any> {
        val n = request["n"] ?: throw IllegalArgumentException("n is required")
        var result: Long
        val processTime = measureTimeMillis {
            result = calculateLucasNumber(n)
        }
        return mapOf("result" to result, "process_time" to processTime)
    }

    private fun calculateLucasNumber(n: Int): Long {
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