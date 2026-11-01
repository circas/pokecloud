package com.example.pokemontest

import android.content.Context
import com.lagradost.cloudstream3.HomePageResponse
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.MainPageRequest
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.mainPageOf
import com.lagradost.cloudstream3.newHomePageResponse
import com.lagradost.cloudstream3.newMovieLoadResponse
import com.lagradost.cloudstream3.newMovieSearchResponse
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.newExtractorLink

private const val TEST_STREAM =
    "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"

@CloudstreamPlugin
class PokemonTestPlugin : Plugin() {
    override fun load(context: Context) {
        registerMainAPI(PokemonTestProvider())
    }
}

class PokemonTestProvider : MainAPI() {
    override var name = "PokemonTest"
    override var mainUrl = "https://test-streams.mux.dev"
    override var lang = "en"
    override val hasMainPage = true
    override val mainPage = mainPageOf(
        "pokemon-test" to "Pokémon – Test Bölümü"
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {
        val item = newMovieSearchResponse(
            name = "Pokémon – Test Bölümü",
            url = "pokemon-test",
            type = TvType.Movie
        )
        return newHomePageResponse(request, listOf(item))
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val matches = query.isBlank() ||
            "pokemon".contains(query, ignoreCase = true) ||
            "test".contains(query, ignoreCase = true)

        return if (matches) {
            listOf(
                newMovieSearchResponse(
                    name = "Pokémon – Test Bölümü",
                    url = "pokemon-test",
                    type = TvType.Movie
                )
            )
        } else {
            emptyList()
        }
    }

    override suspend fun load(url: String) =
        newMovieLoadResponse(
            name = "Pokémon – Test Bölümü",
            url = url,
            type = TvType.Movie,
            dataUrl = TEST_STREAM
        )

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        callback(
            newExtractorLink(
                source = name,
                name = "HLS Test Stream",
                url = data,
                type = ExtractorLinkType.M3U8
            )
        )
        return true
    }
}
