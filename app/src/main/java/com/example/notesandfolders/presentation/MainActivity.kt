/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.notesandfolders.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.notesandfolders.R
import com.example.notesandfolders.presentation.theme.NotesAndFoldersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    NotesAndFoldersTheme {
        val listState = rememberScalingLazyListState()

        Scaffold(
            timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
        ) {
            val contentModifier = Modifier.fillMaxWidth()
            val iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)

            ScalingLazyColumn(
                modifier = contentModifier,
                state = listState,
                autoCentering = AutoCenteringParams(itemIndex = 0)
            ) {
                item {
                    Text(
                        modifier = contentModifier,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary,
                        text = stringResource(R.string.hello_world, greetingName)
                    )
                }
                item { NoteCard(contentModifier, iconModifier) }

                item { CreateNoteButton(contentModifier, iconModifier) }
            }
        }

    }
}

@Preview(
    widthDp = WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}