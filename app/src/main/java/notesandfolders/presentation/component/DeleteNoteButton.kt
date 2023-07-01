package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Note

@Composable
fun DeleteNoteButton(
    context: Context,
    note: Note,
    swipeDismissibleNavController: NavHostController,
    backgroundPainter: Painter
) {
    Card(
        onClick = {
            swipeDismissibleNavController.popBackStack()
            AppDatabase.getDatabase(context).noteDAO().delete(note)
        },
        content = { Text("Delete") },
        shape = RoundedCornerShape(10.dp),
        backgroundPainter = backgroundPainter
    )
}
