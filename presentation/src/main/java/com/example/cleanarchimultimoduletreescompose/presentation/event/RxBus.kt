package com.example.cleanarchimultimoduletreescompose.presentation.event

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val publishEventBus = PublishSubject.create<Event>()

    fun publishEvent(o: Event) {
        publishEventBus.onNext(o)
    }

    val publishEventObservable: Observable<Event>
        get() {
            return publishEventBus
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}