// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
}
