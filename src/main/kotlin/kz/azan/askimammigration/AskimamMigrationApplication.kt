package kz.azan.askimammigration

import kz.azan.askimammigration.exporter.Exporter
import kz.azan.askimammigration.importer.Importer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AskimamMigrationApplication(
    private val exporter: Exporter,
    private val importer: Importer,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        importer.cleanup()

//        exporter.copyTopics()
//        exporter.copyMessages()
//        exporter.copyFavorites()
//        exporter.copyProfiles()
    }
}

fun main(args: Array<String>) {
    runApplication<AskimamMigrationApplication>(*args)
}
