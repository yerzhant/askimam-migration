package kz.azan.askimammigration

import kz.azan.askimammigration.firebase.Exporter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AskimamMigrationApplication(
    private val exporter: Exporter,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        exporter.transfer()
    }
}

fun main(args: Array<String>) {
    runApplication<AskimamMigrationApplication>(*args)
}
