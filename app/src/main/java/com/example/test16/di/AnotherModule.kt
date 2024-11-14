package com.example.test16.di

import com.example.test16.ui.ListFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module {

    scope<ListFragment> {
        scoped( qualifier = named("example")){
            "Anything Class"
        }

        scoped(qualifier = named("TheEnd")) {
            "The End"
        }
    }
}