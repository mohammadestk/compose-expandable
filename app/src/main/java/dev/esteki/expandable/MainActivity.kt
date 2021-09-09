package dev.esteki.expandable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.esteki.expandable.ui.theme.ExpandableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpandableTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Expendables Demo")
                            },
                        )
                    },
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Full custom",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableFullCustom()
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "With leading icon",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableWithIcon()
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Without leading icon",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableWithoutIcon()
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Custom expand icon",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableCustomExpand()
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Custom expand animation",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableCustomExpandAnimation()
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Custom content animation",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Card(
                            modifier = Modifier.padding(16.dp),
                            elevation = 4.dp
                        ) {
                            ExpandableCustomContentAnimation()
                        }
                        Spacer(modifier = Modifier.height(300.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableWithIcon() {
    val expanded = remember { mutableStateOf(false) }

    Expandable(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        expanded = expanded.value,
        onExpandChanged = {
            expanded.value = it
        },
        leading = {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Filled.Call,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(20.dp))
        },
        title = { CardTitle() },
        content = { ExpandableContent() },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableWithoutIcon() {
    val expanded = remember { mutableStateOf(false) }

    Expandable(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        expanded = expanded.value,
        onExpandChanged = {
            expanded.value = it
        },
        title = { CardTitle() },
        content = { ExpandableContent() },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCustomExpand() {
    val expanded = remember { mutableStateOf(false) }

    Expandable(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        expanded = expanded.value,
        onExpandChanged = {
            expanded.value = it
        },
        title = { CardTitle() },
        content = { ExpandableContent() },
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCustomExpandAnimation() {
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
        title = { CardTitle() },
        content = { ExpandableContent() },
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCustomContentAnimation() {
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
        title = { CardTitle() },
        content = { ExpandableContent() },
        contentAnimation = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableFullCustom() {
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
        title = { CardTitle() },
        content = { ExpandableContent() },
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
}


@Composable
private fun ExpandableContent() {
    Column {
        repeat(5) {
            Text(text = "Item ${it + 1}")
        }
    }
}

@Composable
private fun RowScope.CardTitle() {
    Text(
        modifier = Modifier
            .weight(8f),
        text = "Call",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6
    )
}


@Preview(showBackground = true)
@Composable
fun ExpandableWithIconPreview() {
    ExpandableTheme {
        ExpandableWithIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableWithoutIconPreview() {
    ExpandableTheme {
        ExpandableWithoutIcon()
    }
}