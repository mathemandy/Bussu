package com.android.countrysearch.lib_country_search.remote.utils

import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val path  = request.path?.replace("%3B", ";")
        return when (path) {
            getQueryOne() -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(SEARCH_RESPONSE_PATH))
            }
            "$REQUEST_PATH$NO_MATCH_SEARCH_QUERY?fields=${getFields()}" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(NO_MATCH_RESPONSE_PATH)
                    )
            }
            NO_MATCH_COUNTRY_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(getJson(NOT_FOUND_RESPONSE_PATH))
            }
            COUNTRY_NAME -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(CHAR_DETAIL_RESPONSE_PATH))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${path}")
        }

    }

    companion object {
        fun getQueryOne() : String {
          val match =  "$REQUEST_PATH$SEARCH_QUERY?fields=${getFields()}"
            return  match
        }

        fun getFields(): String{
            return "name;flag;currencies;capital"
        }


    }


}
