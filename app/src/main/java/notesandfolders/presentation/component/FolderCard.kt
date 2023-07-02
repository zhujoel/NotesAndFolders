package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.zdxu.notesandfolders.R
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.navigation.Screen
import java.util.UUID

@Composable
fun FolderCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: UUID,
    swipeDismissibleNavController: NavHostController,
) {
    var folder = AppDatabase.getDatabase(context).folderDao().get(folderId)

    Card(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Folder.route + "/$folderId")
        },
        content = {
            Row(
            verticalAlignment = Alignment.CenterVertically,
            // Fill the container height but not its width as chips have fixed size height but we
            // want them to be able to fit their content
            modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.folder),
                contentDescription = "",
                modifier = iconModifier)
            Spacer(modifier = Modifier.size(6.dp))
            Text (
                folder.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1)
            } },
        modifier = modifier,
    )
}