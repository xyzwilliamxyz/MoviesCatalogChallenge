package com.poc.moviescatalog.domain.converter

import com.poc.moviescatalog.data.networking.response.ChangeResponse
import com.poc.moviescatalog.domain.entities.Change

object ChangeConverter {

    fun fromResponse(changeResponse: ChangeResponse): Change {
        return Change(changeResponse.id, changeResponse.adult)
    }

    fun fromResponse(changeResponse: List<ChangeResponse>): List<Change> {
        val changes = mutableListOf<Change>()
        changeResponse.forEach {
            changes.add(fromResponse(it))
        }
        return changes
    }
}
