package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.component.DeleteNoteButton
import java.text.SimpleDateFormat
import java.util.UUID

@Composable
fun NoteScreen(
    context: Context,
    modifier: Modifier = Modifier,
    noteId: UUID,
    swipeDismissibleNavController: NavHostController
) {
    var note = AppDatabase.getDatabase(context).noteDAO().get(noteId)
    val listState = rememberScalingLazyListState()
    var buttonBackgroundPainter = CardDefaults.cardBackgroundPainter(
        startBackgroundColor = Color.Yellow.copy(alpha = 0.30f).compositeOver(MaterialTheme.colors.background),
        endBackgroundColor = MaterialTheme.colors.onSurfaceVariant.copy(alpha = 0.20f)
            .compositeOver(MaterialTheme.colors.background),
        gradientDirection = LocalLayoutDirection.current)

    Scaffold(
        timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
    ) {
        ScalingLazyColumn(modifier = modifier) {
            item {
                Text(note.content)
            }
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ){
                    Text(
                        text = "Created " + SimpleDateFormat("dd/MM/yyyy").format(note.createdAt),
                        fontSize = 12.sp
                    )
                }
            }
            item { DeleteNoteButton(context, note, swipeDismissibleNavController, buttonBackgroundPainter) }
        }
    }
}