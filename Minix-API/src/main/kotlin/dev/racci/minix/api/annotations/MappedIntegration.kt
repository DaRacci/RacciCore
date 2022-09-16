package dev.racci.minix.api.annotations

import dev.racci.minix.api.integrations.IntegrationManager
import dev.racci.minix.api.plugin.MinixPlugin
import org.apiguardian.api.API
import kotlin.reflect.KClass

/**
 * Marks an Integrations class to be registered.
 *
 * @property pluginName The name of the plugin that this integration is for.
 * @property parent The KClass of your plugin.
 * @property integrationManager The IntegrationManager that handles this integration type.
 */
@API(status = API.Status.EXPERIMENTAL, since = "4.0.0")
annotation class MappedIntegration(
    val pluginName: String,
    val parent: KClass<out MinixPlugin>,
    val integrationManager: KClass<out IntegrationManager<*>>
)
