package com.vervaintech.cakeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import com.vervaintech.cakeshop.ui.screens.home.HomeScreen
import com.vervaintech.cakeshop.ui.theme.CakeShopTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			CakeShopTheme {
				Surface {
					Navigator(HomeScreen)
				}
			}
		}
	}
}
