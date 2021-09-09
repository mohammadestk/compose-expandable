
object ProjectDependencies {
    const val EXPANDABLE = ":expandable"
}

object Dependencies {
    const val CORE = "androidx.core:core-ktx:${DependenciesVersion.CORE}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${DependenciesVersion.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:1.4.0"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${DependenciesVersion.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${DependenciesVersion.COMPOSE}"
    const val COMPOSE_UI_TOOLING_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${DependenciesVersion.COMPOSE}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${DependenciesVersion.COMPOSE}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.3.1"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${DependenciesVersion.KOTLIN}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${DependenciesVersion.JUNIT}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${DependenciesVersion.EXT_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${DependenciesVersion.ESPRESSO}"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${DependenciesVersion.COMPOSE}"
}

object DependenciesVersion {
    const val COMPOSE = "1.0.2"
    const val KOTLIN = "1.5.21"
    const val CORE = "1.6.0"
    const val JUNIT = "4.13.2"
    const val EXT_JUNIT = "1.1.3"
    const val ESPRESSO = "3.4.0"
    const val MATERIAL = "1.4.0"
    const val APP_COMPAT = "1.3.1"
}