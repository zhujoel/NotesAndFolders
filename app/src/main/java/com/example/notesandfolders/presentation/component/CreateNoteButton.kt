package com.example.notesandfolders.presentation.component

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
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
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.R
import com.example.notesandfolders.presentation.wearableExtender
import java.util.*

fun SharedPreferences.getStringSetAsCopy(key: String, defaultValue: Set<String>): HashSet<String>{
    var set = this.getStringSet(key, defaultValue)
    return HashSet(set)
}

@Composable
fun CreateNoteButton(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: String, // Folder to create the folder into
) {

    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var folderContent = preferences.getStringSetAsCopy(folderId, HashSet<String>())
    var inputTextKey = "input-key"
    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { data ->
                val results: Bundle = RemoteInput.getResultsFromIntent(data)
                val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
                var preferenceEditor = preferences.edit()
                var noteId = "note_"+ UUID.randomUUID().toString()
                folderContent.add(noteId)
                preferenceEditor.putStringSet(folderId, folderContent)
                preferenceEditor.putString(noteId, newInputText as String)
                preferenceEditor.commit()
            }
        }

    // Intent
    val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(inputTextKey)
            .setLabel("Enter your note name")
            .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }.build()
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