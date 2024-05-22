plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.vervaintech.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
	implementation(project(":domain"))
	implementation(libs.kotlin.coroutines)
	implementation(libs.koin.core)
	implementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(libs.junit)
	implementation(libs.kotlin.test)
	implementation(libs.kotlin.coroutines.test)
	testImplementation(libs.mockk)
}
