package net.johanbasson.document.infrastructure

import net.johanbasson.document.infrastructure.config.ServerConfig
import net.johanbasson.document.infrastructure.routes.ContractRoutes
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.ServerFilters
import org.http4k.filter.ServerFilters.CatchLensFailure
import org.http4k.server.Netty
import org.http4k.server.asServer

class Server(
    config: ServerConfig,
) {

    private val contractRoutes = ContractRoutes()
    private val app = contractRoutes.routes

    private val appWithFilters =   DebuggingFilters
        .PrintRequestAndResponse()
        .then(ServerFilters.CatchAll())
        .then(CatchLensFailure())
        .then(app)

    private val netty = appWithFilters.asServer(Netty(config.port))

    fun start(): Server {
        netty.start()
        return this
    }

    fun stop(): Server {
        netty.stop()
        return this
    }

    fun port(): Int = netty.port()
}