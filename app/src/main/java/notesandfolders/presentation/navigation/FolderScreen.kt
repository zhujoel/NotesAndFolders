package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AutoCenteringParams
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
import notesandfolders.presentation.component.CreateFolderButton
import notesandfolders.presentation.component.CreateNoteButton
import notesandfolders.presentation.component.DeleteFolderButton
import notesandfolders.presentation.component.FolderCard
import notesandfolders.presentation.component.NoteCard
import java.util.UUID

@Composable
fun FolderScreen(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: UUID,
    swipeDismissibleNavController: NavHostController
) {
    var isRootFolder = folderId == UUID(0L, 0L)
    var folder =  AppDatabase.getDatabase(context).folderDao().get(folderId)
    var subFolders = AppDatabase.getDatabase(context).folderDao().getChildFolders(folderId).filter { folder -> folder.id != UUID(0L, 0L) }
    var notes = AppDatabase.getDatabase(context).noteDAO().getChildNotes(folderId)
    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
    ) {
        ScalingLazyColumn(
            modifier = modifier,
            state = listState,
            autoCentering = AutoCenteringParams(itemIndex = 0)
        ) {
            if (!isRootFolder) {
                item { Text(folder.title) }
            }
            items(
                count = subFolders.size,
                key = { subFolders[it].id },
                itemContent = { index ->
                    FolderCard(
                        context,
                        modifier,
                        iconModifier,
                        subFolders[index].id,
                        swipeDismissibleNavController
                    )
                })
            items(
                count = notes.size,
                key = { notes[it].id },
                itemContent = { index ->
                    NoteCard(
                        context,
                        modifier,
                        notes[index].id,
                        swipeDismissibleNavController
                    )
                })
            item {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    CreateFolderButton(context, folderId)
                    Spacer(modifier = Modifier.size(2.dp))
                    CreateNoteButton(context, folderId)
                }
            }
            if (!isRootFolder) {
                item {
                    DeleteFolderButton(
                        context,
                        folder,
                        swipeDismissibleNavController
                    )
                }
            }
        }
    }
}