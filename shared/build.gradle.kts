plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.4.10"
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
// Ktor

                implementation("io.ktor:ktor-client-core:1.6.3")
                implementation("io.ktor:ktor-client-cio:1.6.3")
                implementation("io.ktor:ktor-client-serialization:1.6.3")
                implementation("io.ktor:ktor-client-websockets:1.6.3")
                implementation("io.ktor:ktor-client-logging:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
                implementation("ch.qos.logback:logback-classic:1.2.6")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.3")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.isaev.myktormultiplatformtestchat"
    compileSdk = 32
    defaultConfig {
        minSdk = 25
        targetSdk = 32
    }
}