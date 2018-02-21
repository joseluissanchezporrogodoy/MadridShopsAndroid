package com.joseluissanchezporrogodoy.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
internal class ResponseEntity (
        val result: List<Entity>
)
