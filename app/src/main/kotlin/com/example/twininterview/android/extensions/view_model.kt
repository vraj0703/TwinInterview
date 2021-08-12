package com.example.twininterview.android.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

inline fun <reified T : ViewModel> observers(vm: T, body: T.() -> Unit) {
    vm.body()
}

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observe(liveData: L?, body: (T?) -> Unit) =
    liveData?.observe(this, Observer(body))