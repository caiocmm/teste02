package com.example.testegit.features.main.module

import android.app.Application
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest : KoinTest {

    @Test
    fun `check modules injections`() {

        val context = mockk<Application>()

        startKoin {
            androidContext(context)
            modules(listOf(gitModule))
        }.checkModules()

    }

    @AfterEach
    fun clearUp() = stopKoin()
}