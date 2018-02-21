package com.joseluissanchez_porrogodoy.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ResponseEntity (
        val result: List<Entity>
)
