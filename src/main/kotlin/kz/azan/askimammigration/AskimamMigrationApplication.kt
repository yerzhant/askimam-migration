package kz.azan.askimammigration

import kz.azan.askimammigration.exporter.Exporter
import kz.azan.askimammigration.importer.Importer
import kz.azan.askimammigration.migrator.Migrator
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val anonymousUserId = -1

@SpringBootApplication
class AskimamMigrationApplication(
    private val exporter: Exporter,
    private val importer: Importer,
    private val migrator: Migrator,
) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        logger.info("Started")

        if (args[0] == "import") {
            logger.info("Importing...")

            importer.cleanup()
            exporter.copyTopics()
            exporter.copyMessages()
            exporter.copyFavorites()
            exporter.copyProfiles()
        }

        if (args[0] == "migrate") {
            logger.info("Migrating...")

            migrator.cleanup()
            migrator.fillUserIdsInProfiles()
            migrator.migrateChats()
            migrator.migrateMessages()
            migrator.migrateFavorites()
            migrator.downloadAudios()
        }

        logger.info("Finished")
    }
}

fun main(args: Array<String>) {
    runApplication<AskimamMigrationApplication>(*args)
}
