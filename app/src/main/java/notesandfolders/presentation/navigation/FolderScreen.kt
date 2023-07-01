package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AutoCenteringParams
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
import notesandfolders.presentation.component.CreateFolderButton
import notesandfolders.presentation.component.CreateNoteButton
import notesandfolders.presentation.component.DeleteFolderButton
import notesandfolders.presentation.component.FolderCard
import notesandfolders.presentation.component.NoteCard
import java.text.SimpleDateFormat
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
    var subFolders = AppDatabase.getDatabase(context).folderDao().getChildFolders(folderId).filter {fold -> fold.id != UUID(0L, 0L) }
    var notes = AppDatabase.getDatabase(context).noteDAO().getChildNotes(folderId)
    val listState = rememberScalingLazyListState()
    var buttonBackgroundPainter = CardDefaults.cardBackgroundPainter(
        startBackgroundColor = Color.Yellow.copy(alpha = 0.30f).compositeOver(MaterialTheme.colors.background),
        endBackgroundColor = MaterialTheme.colors.onSurfaceVariant.copy(alpha = 0.20f)
            .compositeOver(MaterialTheme.colors.background),
        gradientDirection = LocalLayoutDirection.current)
    var shape = RoundedCornerShape(10.dp)

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
            if (!isRootFolder) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = "Edited " + SimpleDateFormat("dd/MM/yyyy HH:mm").format(folder.lastUpdated),
                            fontSize = 12.sp
                        )
                    }
                }
            }
            item {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    CreateFolderButton(context, folderId, buttonBackgroundPainter, shape)
                    Spacer(modifier = Modifier.size(2.dp))
                    CreateNoteButton(context, folderId, buttonBackgroundPainter, shape)
                }
            }
            if (!isRootFolder) {
                item { DeleteFolderButton(context, folder, swipeDismissibleNavController, buttonBackgroundPainter, shape) }
            }
        }
    }
}