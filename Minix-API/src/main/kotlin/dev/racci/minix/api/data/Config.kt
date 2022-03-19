package dev.racci.minix.api.data

import dev.racci.minix.api.annotations.MappedConfig
import dev.racci.minix.api.plugin.Minix
import dev.racci.minix.api.utils.getKoin
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment
import java.util.UUID
import java.util.logging.Level

@ConfigSerializable
@MappedConfig(Minix::class, "config.conf")
class Config {

    @Comment("This server unique uuid (Please don't change this)")
    var serverUUID: UUID = UUID.randomUUID()

    @Comment("What log level should be used?")
    var loggingLevel: Level = Level.INFO
        set(value) {
            field = value
            getKoin().get<Minix>().logger.level = value
        }

    @Comment(
        """
        Should sentry be enabled?
        This will send errors relating to Minix and my plugins,
        I do not collect / sell any personal information,
        however some information like your server version, fork, and ip will be
        sent for identification and error tracking.
        """
    )
    var sentryEnabled: Boolean = true
}
