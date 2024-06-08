package com.example.uicomponentsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uicomponentsexample.ui.theme.UIComponentsExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIComponentsExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Buttons()
                        Divider()
                        Choosers()
                    }

                }
            }
        }
    }
}

@Composable
fun Buttons(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(onClick = { /*TODO*/ }) {
            Text("Basic button")
        }
        Button(onClick = { /*TODO*/ }, enabled = false) {
            Text("Disabled button")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("Icon button")
            Icon(Icons.Filled.Check, contentDescription = "Localized description")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Text button")
        }
    }
}

@Composable
fun Choosers(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val checkedState = remember { mutableStateOf(true) }
        Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })

        var anotherCheckedState by remember { mutableStateOf(true) }
        TextedCheckBox(text = "Agree",
            checked = anotherCheckedState,
            onCheckedChange = { anotherCheckedState = it })

        FilterChipExample()

        IconToggleButtonSample()

        RadioButtonSample()

        /*var state by remember { mutableStateOf(true) }
        RadioButton(
            selected = state,
            onClick = { state = !state   }
        )

        var anotherState by remember { mutableStateOf(true) }
        TextedRadioButton(text = "Yes",
            selected = anotherState,
            onClick = { anotherState = true })

         */
    }
}

@Composable
fun TextedCheckBox(
    text: String,
    modifier: Modifier = Modifier,
    checked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    // https://developer.android.com/develop/ui/compose/components/checkbox
    val checkedState = remember { mutableStateOf(checked) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        Text(text)
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
@Preview
fun IconToggleButtonSample() {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
        if (checked) {
            Icon(Icons.Filled.Lock, contentDescription = "Localized description")
        } else {
            // Extra icons imported in Gradle file
            Icon(Icons.Filled.LockOpen, contentDescription = "Localized description")
        }
    }
}

@Composable
fun TextedRadioButton(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    onClick: () -> Unit = {}
) {
    val selectedState by remember { mutableStateOf(selected) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        Text(text)
        RadioButton(selected = selected, onClick = onClick)
    }
}

@Composable
// https://www.composables.com/material/radiobutton
fun RadioButtonSample() {
    // We have two radio buttons and only one can be selected
    var state by remember { mutableStateOf(true) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Row(
        Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Yes")
        RadioButton(
            selected = state,
            onClick = { state = true }
        )
        Text("No")
        RadioButton(
            selected = !state,
            onClick = { state = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExample() {
    var selected by remember { mutableStateOf(true) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text("Filter chip")
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}


@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    UIComponentsExampleTheme {
        Buttons()
    }
}

@Preview(showBackground = true)
@Composable
fun ChoosersPreview() {
    UIComponentsExampleTheme {
        Choosers()
    }
}