package com.example.twininterview.android.data.domain.food.repository

import com.example.twininterview.android.data.domain.food.model.Food
import javax.inject.Inject
import javax.inject.Singleton
import com.example.twininterview.android.InterviewApplication
import com.example.twininterview.android.extensions.fromJson
import com.example.twininterview.android.extensions.fromTypeToken
import com.google.gson.reflect.TypeToken
import com.example.twininterview.android.extensions.loadJSONFromAsset
import com.example.twininterview.android.extensions.gson
import java.lang.Exception
import com.google.gson.Gson
import java.util.*

@Singleton
class InMemoryFoodDataRepository @Inject constructor(
    private val application: InterviewApplication
) : FoodDataRepository {
    companion object {
        const val FODD_JSON_FILE_NAME = "food.json"
    }

    var foods: List<Food>? = null

    override suspend fun loadFood(): Pair<Boolean?, Throwable?> {
        application.loadJSONFromAsset(FODD_JSON_FILE_NAME).apply {
            if (first != null) {
                val type = object : TypeToken<List<Food>>() {}.type
                foods = Gson().fromJson(first, type)
                return Pair(true, null)
            }
            return Pair(false, second)
        }
    }

    override suspend fun getFood(): Pair<List<Food>?, Throwable?> {
        return if (foods != null) Pair(foods, null)
        else Pair(null, Exception("Food is not loaded from assets"))
    }
}
