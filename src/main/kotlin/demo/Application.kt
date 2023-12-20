package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import()
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}