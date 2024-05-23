package com.vervaintech.cakeshop

import android.app.Application
import com.vervaintech.cakeshop.di.initKoin

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin()
	}
}
