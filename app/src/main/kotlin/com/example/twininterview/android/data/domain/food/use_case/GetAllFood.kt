package com.example.twininterview.android.data.domain.food.use_case

import com.example.twininterview.android.data.domain.base.CoroutinesContextFacade
import com.example.twininterview.android.data.domain.base.Usecase
import com.example.twininterview.android.data.domain.food.repository.FoodDataRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.twininterview.android.data.domain.food.model.Food
import kotlinx.coroutines.channels.produce
import java.lang.Exception
import kotlinx.coroutines.channels.ReceiveChannel

class GetAllFood @Inject constructor(
    private val contextFacade: CoroutinesContextFacade,
    private val foodDataRepository: FoodDataRepository
) : Usecase<Unit, List<Food>> {
    override suspend fun invoke(param: Unit): List<Food> = coroutineScope {
        withContext(contextFacade.default) {
            foodDataRepository.getFood().run {
                if (first != null) return@run first ?: listOf<Food>()
                else throw second ?: Exception("Use Case GetAllFood")
            }
        }
    }
}