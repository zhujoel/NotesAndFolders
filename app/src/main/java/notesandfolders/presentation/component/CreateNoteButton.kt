package notesandfolders.presentation.component

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.R
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Folder
import notesandfolders.entities.Note
import notesandfolders.presentation.wearableExtender
import java.util.*
import kotlin.collections.HashSet

fun SharedPreferences.getStringSetAsCopy(key: String, defaultValue: Set<String>): HashSet<String>{
    var set = this.getStringSet(key, defaultValue)
    return HashSet(set)
}

@Composable
fun CreateNoteButton(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: UUID, // Folder to create the folder into
) {
    var inputTextKey = "new-note-input"
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it.data?.let { data ->
            val results: Bundle = RemoteInput.getResultsFromIntent(data)
            val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
            Thread{
                AppDatabase.getDatabase(context).noteDAO().insert(Note(UUID.randomUUID(), folderId, newInputText.toString()))
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Chip(
            onClick = { launcher.launch(intent) },
            label = {
                Text(
                    stringResource(R.string.create_note_button_label),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.create_note_button_description),
                    modifier = iconModifier
                )
            },
            modifier = modifier
        )
    }
}