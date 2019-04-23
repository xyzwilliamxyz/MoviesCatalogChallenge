package com.poc.moviescatalog.domain.converter

import com.poc.moviescatalog.data.networking.response.LanguageResponse
import com.poc.moviescatalog.domain.entities.Language

object LanguageConverter {

    fun fromResponse(languageResponse: LanguageResponse): Language {
        return Language(languageResponse.name, languageResponse.iso)
    }

    fun fromResponse(languageResponse: List<LanguageResponse>?): List<Language> {
        val languages = mutableListOf<Language>()
        languageResponse?.forEach {
            languages.add(fromResponse(it))
        }
        return languages
    }
}
