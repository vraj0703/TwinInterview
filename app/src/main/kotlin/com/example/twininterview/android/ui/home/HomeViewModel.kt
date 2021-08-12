package com.example.twininterview.android.ui.home

import android.util.Log
import com.example.twininterview.android.data.domain.food.use_case.GetAllFood
import com.example.twininterview.android.data.domain.food.use_case.LoadFood
import com.example.twininterview.android.ui.base.BaseViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.example.twininterview.android.data.domain.food.model.Food
import com.example.twininterview.android.ui.base.Event
import com.example.twininterview.android.ui.splash.model.SplashScreenNavigation

class HomeViewModel @Inject constructor(
    private val loadFood: LoadFood,
    private val getAllFood: GetAllFood
) : BaseViewModel() {

    val loaded = MutableLiveData<Boolean?>()
    var allFoods: List<Food>? = null
    val displayFood = MutableLiveData<List<Food>?>()

    fun load() {
        launch {
            runCatching {
                val result = loadFood.invoke(Unit)
                loaded.postValue(result)
            }.onFailure { exception ->
                Log.e("HomeViewModel", "Failure: ${exception.message}")
            }
        }
    }

    fun getFoods() {
        launch {
            runCatching {
                allFoods = getAllFood.invoke(Unit)
                displayFood.postValue(allFoods)
            }.onFailure { exception ->
                Log.e("HomeViewModel", "Failure: ${exception.message}")
            }
        }
    }

    fun searchFood(query: String) {
        launch {
            runCatching {
                allFoods?.let { list ->
                    if (query.length == 0) displayFood.postValue(list)
                    else list.filter { it.title.contains(query, true) }.let { displayFood.postValue(it) }
                }
            }.onFailure { exception ->
                Log.e("HomeViewModel", "Failure: ${exception.message}")
            }
        }
    }
}
