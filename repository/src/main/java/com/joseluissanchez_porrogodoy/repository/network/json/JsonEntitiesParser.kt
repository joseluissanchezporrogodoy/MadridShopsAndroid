package com.joseluissanchez_porrogodoy.repository.network.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

    internal class JsonEntitiesParser {
        val mapper = jacksonObjectMapper()

        inline fun <reified T: Any> parse(json: String): T {
            return this.mapper.readValue<T>(json)
        }
    }

