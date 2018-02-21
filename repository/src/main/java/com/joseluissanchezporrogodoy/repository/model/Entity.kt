package com.joseluissanchezporrogodoy.repository.model
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Entity (
        val type: Int,
        val id: Long,
        val databaseId: Long,
        val name: String,
        @JsonProperty("description_en") val description: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en")val openingHours: String,
        val address: String
)
