package com.example.mad.ui.repository

import android.util.Log
import com.example.mad.data.api.NumbersApi
import com.example.mad.data.api.NumbersApiService
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Numbers
import com.example.mad.ui.screens.numberType.TYPE_MATH
import com.example.mad.ui.screens.numberType.TYPE_TRIVIA
import com.example.mad.ui.screens.numberType.TYPE_YEAR
import kotlinx.coroutines.withTimeout

class NumbersRepository {
    private val numbersApiService: NumbersApiService = NumbersApi.createApi()

    /**
     * suspend function that calls a suspend function from the numbersApi call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomNumber(numberType: String) : Resource<Numbers> {
        val apiNumberType = when(numberType) {
            TYPE_MATH -> "math"
            TYPE_TRIVIA -> "trivia"
            TYPE_YEAR -> "year"
            else -> "trivia"
        }

        val response = try {
            withTimeout(5_000) {
                numbersApiService.getRandomNumber(apiNumberType)
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}