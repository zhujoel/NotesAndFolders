package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import java.util.UUID

@Composable
fun NoteScreen(
    context: Context,
    modifier: Modifier = Modifier,
    noteId: UUID
) {
    ScalingLazyColumn(modifier = modifier){
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "You are now in the note screen, $noteId",
            )
        }
    }
}