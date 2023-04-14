package com.example.notesandfolders.presentation

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.*
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.R


fun GetInputIntent(
    context: Context,
    id: String
): Intent {
    var inputTextKey = "folder-title-key"
    val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(inputTextKey)
            .setLabel("Enter your folder name")
            .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }.build()
    )
    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
    return intent;
}

@Composable
fun CreateFolderButton(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: String, // Folder to create the folder into
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var folderContent = preferences.getStringSet(folderId, HashSet<String>())
    var inputTextKey = "folder-title-key"
    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { data ->
                val results: Bundle = RemoteInput.getResultsFromIntent(data)
                val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
                var preferenceEditor = preferences.edit()
                folderContent?.add(newInputText as String)
                preferenceEditor.putStringSet(inputTextKey, folderContent)
                preferenceEditor.commit()
            }
        }

    var intent = GetInputIntent(context, folderId)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Chip(
            onClick = { launcher.launch(intent) },
            label = {
                Text(
                    stringResource(R.string.create_folder_button_label),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.create_folder_button_description),
                    modifier = iconModifier
                )
            },
            modifier = modifier
        )
    }
}


@Composable
fun CreateNoteButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Chip(
            onClick = { },
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

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier,
        appImage = {
            Icon(
                imageVector = Icons.Rounded.Message,
                contentDescription = "triggers open message action",
                modifier = iconModifier
            )
        },
        appName = { Text("App Name") },
        time = { },
        title = { Text("Title") },
        onClick = { /* ... */ }
    ) {
        Text ("On my way!")
    }
}