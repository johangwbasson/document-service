package net.johanbasson.document.infrastructure.routes

import net.johanbasson.document.infrastructure.api.HealthEndpoint
import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.core.HttpHandler
import org.http4k.format.KotlinxSerialization

class ContractRoutes {

    val routes: HttpHandler =
        contract {
            renderer =
                OpenApi3(
                    apiInfo =
                        ApiInfo(
                            title = "Document API",
                            version = "1.0.0",
                            description = "Document API",
                        ),
                    json = KotlinxSerialization,
                )
            descriptionPath = "/openapi.json"

            routes += HealthEndpoint.route
        }
}