package com.example.iainteracitvemovies.data.network

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
class Endpoints {
    companion object{
        const val URL_BASE = "https://stage-api.cinepolis.com"
        const val URL_BASE_AWS = "http://aws-ami-api.cinepolis.com"
        const val VALIDATE_USER = "/v2/oauth/token"
        const val GET_USER = "/v1/members/profile?country_code=MX"
        const val GET_MOVIES= "/v2/movies?country_code=MX&cinema=61"
    }
}