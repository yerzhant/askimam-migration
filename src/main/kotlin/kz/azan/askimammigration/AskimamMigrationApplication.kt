package kz.azan.askimammigration

import kz.azan.askimammigration.exporter.Exporter
import kz.azan.askimammigration.importer.Importer
import kz.azan.askimammigration.migrator.Migrator
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AskimamMigrationApplication(
    private val exporter: Exporter,
    private val importer: Importer,
    private val migrator: Migrator,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
//        importer.cleanup()

//        exporter.copyTopics()
//        exporter.copyMessages()
//        exporter.copyFavorites()
//        exporter.copyProfiles()

        migrator.fillUserIdsInProfiles()
    }
}

fun main(args: Array<String>) {
    runApplication<AskimamMigrationApplication>(*args)
}
