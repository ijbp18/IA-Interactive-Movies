package com.example.iainteracitvemovies

import com.example.iainteracitvemovies.data.network.mappers.toUserUnfoUI
import com.example.iainteracitvemovies.data.network.model.ErrorResponse
import com.example.iainteracitvemovies.data.network.model.UserResponse
import com.example.iainteracitvemovies.data.repository.RepositoryImpl
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.google.common.truth.Truth
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlinx.coroutines.flow.collect

/**
 * Created by Joao Betancourth on 23,enero,2022
 */

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepositoryTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val server = MockWebServer()
    private lateinit var sut: RepositoryImpl
    private lateinit var mockedResponse: String

    private val gson = GsonBuilder().create()

    @Before
    fun init() {
        server.start(8000)
        val baseUrl = server.url("/").toString()
        sut = RepositoryImpl(
            createInternalService(baseUrl),
            coroutinesTestRule.testDispatcherProvider,

            )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    //Retrieving user info from acceptance terms and conditions service
    @Test
    fun verifyOkResult_whenUserConsultCredentialsLogin() {
        runBlocking {
            val httpCode = 200
            val expectedItemListSize = 2

            //Creamos los mocksResponse de ambas respuestas a testear
            val mockedResponseUserLogin = MockResponseFileReader("internalResponses/validate-user-login-200.json").content
            val mockedResponseUserInfo = MockResponseFileReader("internalResponses/get-info-user-200.json").content

            server.enqueue(
                MockResponse()
                    .setResponseCode(httpCode)
                    .setBody(mockedResponseUserLogin)

            )


            server.enqueue(
                MockResponse()
                    .setResponseCode(httpCode)
                    .setBody(mockedResponseUserInfo)

            )
            val expectedModel =
                gson.fromJson(
                    mockedResponse,
                    UserResponse::class.java
                ).toUserUnfoUI()

            val actualFlow = sut.validateUserInfo(provideLoginUserParameter())
            val stateList = mutableListOf<Result<UserUI>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as Result.Success

            Truth.assertThat(stateList.first()).isInstanceOf(Result.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue.data).isEqualTo(expectedModel)
        }
    }

    //Return failure object and provide error JSON example
    @Test
    fun verifyErrorResponse_whenServerCannotRetrieveSuccessData_onConsultCredentialsLogin() {
        runBlocking {
            val httpCode = 400
            val expectedItemListSize = 2


            //Validamos que se cumpla cuando la primera respuesta sea un 400 y la segunda un 200
            val mockedResponseUserLogin = MockResponseFileReader("internalResponses/bad-request-error-respose-400.json").content
            val mockedResponseUserInfo = MockResponseFileReader("internalResponses/get-info-user-200.json").content

            server.enqueue(
                MockResponse().setResponseCode(httpCode).setBody(mockedResponse)
            )

            server.enqueue(
                MockResponse()
                    .setResponseCode(httpCode)
                    .setBody(mockedResponseUserInfo)
            )

            val expectedModel =
                gson.fromJson(
                    mockedResponseUserLogin,
                    ErrorResponse::class.java
                )


            val actualFlow = sut.validateUserInfo(provideLoginUserParameter())
            val stateList = mutableListOf<Result<UserUI>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as Result.Failure
            Truth.assertThat(stateList.first()).isInstanceOf(Result.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue).isInstanceOf(Result.Failure::class.java)
            Truth.assertThat(expectedModel).isInstanceOf(ErrorResponse::class.java)
            Truth.assertThat(unWrapperValue.error).isEqualTo(expectedModel)
            Truth.assertThat(unWrapperValue.httpCode).isEqualTo(httpCode)
            Truth.assertThat(unWrapperValue.error).isInstanceOf(ErrorResponse::class.java)
        }
    }



    @Test
    fun testNullData_whenServerReturnsEmptyErrorAnd500HttpCode_onConsultCredentialsLogin() {
        runBlocking {
            val httpCode = 500
            mockedResponse = ""
            val expectedItemListSize = 2
            server.enqueue(
                MockResponse().setResponseCode(httpCode).setBody(mockedResponse)
            )

            val actualFlow = sut.validateUserInfo(provideLoginUserParameter())
            val stateList = mutableListOf<Result<UserUI>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as Result.Failure
            Truth.assertThat(stateList.first()).isInstanceOf(Result.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue).isInstanceOf(Result.Failure::class.java)
            Truth.assertThat(unWrapperValue.error).isNull()
        }
    }

    @Test
    fun testNullData_whenServerRetrievesInvalidJsonAsResponseAnd500HttpCode_onConsultCredentialsLogin() {
        runBlocking {
            mockedResponse = "12390-adfjlzxcvklj3q4m52/asdfj234509jaskd.,mzxcv awupqwe"
            val expectedItemListSize = 2
            server.enqueue(
                MockResponse().setResponseCode(500).setBody(mockedResponse)
            )

            val actualFlow = sut.validateUserInfo(provideLoginUserParameter())
            val stateList = mutableListOf<Result<UserUI>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as Result.Failure
            Truth.assertThat(stateList.first()).isInstanceOf(Result.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue).isInstanceOf(Result.Failure::class.java)
            Truth.assertThat(unWrapperValue.error).isNull()

        }
    }

    private fun provideLoginUserParameter(): String {
        return "parametrodepruebas"
    }


}