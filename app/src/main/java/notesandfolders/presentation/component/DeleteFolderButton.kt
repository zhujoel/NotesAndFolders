package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Folder

@Composable
fun DeleteFolderButton(
    context: Context,
    folder: Folder,
    swipeDismissibleNavController: NavHostController
) {
    Card(
        onClick = {
            swipeDismissibleNavController.popBackStack()
            AppDatabase.getDatabase(context).folderDao().delete(folder)
        },
        content = { Text("Delete") },
        shape = RoundedCornerShape(10.dp),
        backgroundPainter = CardDefaults.cardBackgroundPainter(
            startBackgroundColor = Color.Yellow.copy(alpha = 0.30f).compositeOver(MaterialTheme.colors.background),
            endBackgroundColor = MaterialTheme.colors.onSurfaceVariant.copy(alpha = 0.20f)
                .compositeOver(MaterialTheme.colors.background),
            gradientDirection = LocalLayoutDirection.current)
    )
}
