package com.example.twininterview.android.data.domain.food.use_case

import com.example.twininterview.android.data.domain.base.CoroutinesContextFacade
import com.example.twininterview.android.data.domain.base.Usecase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.twininterview.android.data.domain.food.repository.FoodDataRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class LoadFood @Inject constructor(
    private val contextFacade: CoroutinesContextFacade,
    private val foodDataRepository: FoodDataRepository
) : Usecase<Unit, Boolean> {
    override suspend fun invoke(param: Unit): Boolean = coroutineScope {
        withContext(contextFacade.io) {
            foodDataRepository.loadFood().run {
                if (first != null) return@run true
                else throw second ?: Exception("Use Case LoadFood")
            }
        }
    }
}