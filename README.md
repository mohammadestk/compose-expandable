# Compose Expandable ✨

[![Use this template](https://img.shields.io/badge/from-jetpack--compose-brightgreen?style=for-the-badge&logo=android)](https://developer.android.com/jetpack/compose) ![License](https://img.shields.io/badge/license-MIT-green?style=for-the-badge) ![Language](https://img.shields.io/github/languages/top/mohammadestk/compose-expandable?color=blue&logo=kotlin&style=for-the-badge)


A fully customizable and **state hoisted** expandable view developed by **Jetpack Compose**

## Prerequisites 👑

* build.gradle.kts
	```kotlin
	dependencies {
         /** .... **/
		implementation("ir.mohammadesteki:compose-expandable:0.1.1")
	}
	```

## How to use 👣

You can see example provided in app module

* Simple example
    ```kotlin
    val expanded = remember { mutableStateOf(false) }

    Expandable(
        expanded = expanded.value,
        onExpandChanged = {
            expanded.value = it
        },
        title = { /** Your Title **/ },
        content = { /** Your Content **/ },
    )
    ```

* Advanced example
    ```kotlin
    val expanded = remember { mutableStateOf(false) }
        val expandAnimation: State<Float> = animateFloatAsState(
            targetValue = if (expanded.value) 540f else 0f,
            animationSpec = tween(1000, easing = FastOutSlowInEasing)
        )

        Expandable(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            expanded = expanded.value,
            onExpandChanged = {
                expanded.value = it
            },
            title = { /** Your Title **/ },
            content = { /** Your Content **/ },
            contentAnimation = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            ),
            leading = {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Filled.Call,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(20.dp))
            },
            expandAnimation = expandAnimation,
            expand = { modifier ->
                IconButton(
                    modifier = modifier,
                    onClick = {
                        expanded.value = !expanded.value
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        )
    ```

## Features 🎨

- Fully customizable with compose functions
- State hoisted
- Default animations for expand icon and content expansion
- **100% Kotlin**.
- Sample module (Android app).
- 100% Gradle Kotlin DSL setup.
- Dependency versions managed via `buildSrc`.

## Demo ✨

<img src="https://github.com/mohammadestk/compose-expandable/blob/master/demo/sample.gif" width="252" height="486" />

## Gradle Setup 🐘

This project is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the [Dependencies.kt](buildSrc/src/main/kotlin/Dependencies.kt) file in the `buildSrc` folder. This provides convenient auto-completion when writing your gradle files.

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

[![LinkedIn][linkedin-shield]][linkedin-url]

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/mohammadesteki