import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
    signing
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersion.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.CORE)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)

    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROID_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
    androidTestImplementation(TestDependencies.COMPOSE_JUNIT)
}

gradleLocalProperties(project.rootDir)
    .forEach { (name, value) ->
        ext[name.toString()] = value
    }

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    if (project.plugins.findPlugin("com.android.library") != null) {
        from(android.sourceSets.getByName("main").java.srcDirs)
    } else {
        // For pure Kotlin libraries, in case you have them
        from(sourceSets.getByName("main").java.srcDirs)
    }
}

artifacts {
    archives(javadocJar)
    archives(androidSourcesJar)
}

fun getExtraString(name: String) = ext[name]?.toString()

afterEvaluate {
    publishing {
        // Configure maven central repository
        repositories {
            maven {
                name = "sonatype"

                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    print(getExtraString("ossrhUsername"))
                    username = getExtraString("ossrhUsername")
                    password = getExtraString("ossrhPassword")
                }
            }
        }

        // Configure all publications
        publications {
            create<MavenPublication>("release") {

                groupId = "ir.mohammadesteki"
                artifactId = "compose-expandable"
                version = "0.1.1"

                // Two artifacts, the `aar` (or `jar`) and the sources
                if (project.plugins.findPlugin("com.android.library") != null) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }

                // Stub javadoc.jar artifact
                artifact(androidSourcesJar.get())
                artifact(javadocJar.get())

                // Provide artifacts information requited by Maven Central
                pom {
                    name.set("Compose Expandable")
                    description.set("fully customizable expandable developed by jetpack compose")
                    url.set("https://github.com/mohammadestk/compose-expandable")

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("mamad")
                            name.set("Mohammad Esteki")
                            email.set("memestek4@gmail.com")
                        }
                    }
                    scm {
                        url.set("https://github.com/mohammadestk/compose-expandable")
                    }

                }
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used

signing {
    useInMemoryPgpKeys(
        project.ext["signing.keyId"].toString(),
        project.ext["signing.secretKeyRingFile"].toString(),
        project.ext["signing.password"].toString(),
    )
    sign(publishing.publications)
}
