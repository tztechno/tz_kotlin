package mkj21

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NumberController {

    @PostMapping("/check")
    fun checkNumber(@RequestBody request: NumberRequest): String {
        val number = request.value
        return if (number % 2 == 0) "Even" else "Odd"
    }
}

data class NumberRequest(val value: Int)

