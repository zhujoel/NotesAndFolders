package notesandfolders.presentation.component

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.R
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Note
import notesandfolders.presentation.wearableExtender
import java.util.Date
import java.util.UUID

@Composable
fun CreateNoteButton(
    context: Context,
    folderId: UUID, // Folder to create the folder into
    backgroundPainter: Painter
) {
    var inputTextKey = "new-note-input"
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it.data?.let { data ->
            val results: Bundle = RemoteInput.getResultsFromIntent(data)
            val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
            Thread{
                AppDatabase.getDatabase(context).noteDAO().insert(Note(UUID.randomUUID(), folderId, newInputText.toString(), Date()))
                AppDatabase.getDatabase(context).folderDao().updateLastUpdated(folderId, Date())
            }.start()
        }
    }

    // Intent
    val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput
            .Builder(inputTextKey)
            .setLabel("Note")
            .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }
            .build()
    )
    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)

    Card(
        onClick = { launcher.launch(intent) },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight())
            {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.note_add),
                    contentDescription = stringResource(R.string.create_note_button_description),
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text("New", fontWeight = FontWeight.Medium)
            }
        },
        modifier = Modifier.fillMaxWidth(1f),
        shape = RoundedCornerShape(10.dp),
        backgroundPainter = backgroundPainter
    )
}