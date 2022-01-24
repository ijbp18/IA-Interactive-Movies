package com.example.iainteracitvemovies.usecase

import com.example.iainteracitvemovies.data.repository.Repository
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI
import com.example.iainteracitvemovies.domain.usecase.UserUseCaseImpl
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Joao Betancourth on 24,enero,2022
 */
class ValidateUserLoginUseCaseImplTest {
    private val repository = Mockito.mock(Repository::class.java)
    private val sut = UserUseCaseImpl(repository)

    @Test
    fun verifySuccessState_whenUserValidateUserLogin() {
        runBlocking {
            val expectedModel = provideSuccessUserLoginResponseUI()
            given(repository.validateUserInfo(any())).willReturn(
                flowOf(Result.Success(expectedModel))
            )
            val flow = sut.invoke(provideVerifyUserLoginParameters())
            val expectedData = flow.toList().last()

            Truth.assertThat(flow.toList().last()).isInstanceOf(Result.Success::class.java)
            val stateData = expectedData as Result.Success
            Truth.assertThat(stateData.data).isEqualTo(expectedModel)
            Truth.assertThat(stateData.data).isNotNull()
        }
    }

    @Test
    fun verifyLoadingState_whenUseValidateUserLogin(){
        runBlocking {
            given(repository.validateUserInfo(provideVerifyUserLoginParameters())).willReturn(
                flowOf(Result.Loading)
            )
            val flow = sut.invoke(provideVerifyUserLoginParameters())
            Truth.assertThat(flow.toList().last()).isInstanceOf(Result.Loading::class.java)
        }
    }

    @Test
    fun verifyErrorResult_whenUserValidateUserLogin(){
        runBlocking {
            val errorHttpCode = 400
            val expectedModel = provideInfoResponseUI()
            given(repository.validateUserInfo(provideVerifyUserLoginParameters())).willReturn(
                flowOf(Result.Failure(provideInfoResponseUI(), errorHttpCode))
            )
            val flow = sut.invoke(provideVerifyUserLoginParameters())
            val expectedData = flow.toList().last()

            Truth.assertThat(flow.toList().last()).isInstanceOf(Result.Failure::class.java)
            val stateData = expectedData as Result.Failure
            Truth.assertThat(stateData.error).isNotNull()
            Truth.assertThat(stateData.error).isEqualTo(expectedModel)
            Truth.assertThat(stateData.httpCode).isEqualTo(errorHttpCode)
        }
    }

    private fun provideSuccessUserLoginResponseUI(): UserUI {
        return UserUI(
            email = "prueba_testteo@gmail.com",
            first_name = "name",
            last_name= "last name",
            phone_number = "3282737373",
            profile_picture = "url_image",
            card_number = "1112222333"
        )
    }

    private fun provideInfoResponseUI(): ErrorResponseUI {
        return ErrorResponseUI(
            error = "Error",
            error_description = "Something went wrong"
        )
    }


    private fun provideVerifyUserLoginParameters(): String {
        return "97cb383f6fafdb8c813c04e1349cf185acb12b7"
    }
}