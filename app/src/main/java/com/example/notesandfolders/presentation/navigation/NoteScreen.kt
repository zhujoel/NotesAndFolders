package com.example.notesandfolders.presentation.navigation

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.presentation.wearableExtender

@Composable
fun NoteScreen(
    context: Context,
    modifier: Modifier = Modifier,
    noteId: String
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    val inputTextKey = "input_text"
    var notePreference = preferences.getString(inputTextKey, "valueDefault")

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { data ->
                val results: Bundle = RemoteInput.getResultsFromIntent(data)
                val newInputText: CharSequence? = results.getCharSequence(inputTextKey)
                var preferenceEditor = preferences.edit()
                preferenceEditor.putString(inputTextKey, newInputText as String)
                preferenceEditor.commit()
            }
        }

    ScalingLazyColumn(modifier = modifier){
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "You are now in the note screen, $noteId",
            )
        }

        item {
            val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
            val remoteInputs: List<RemoteInput> = listOf(
                RemoteInput.Builder(inputTextKey)
                    .setLabel("Enter your note")
                    .wearableExtender {
                        setEmojisAllowed(false)
                        setInputActionType(EditorInfo.IME_ACTION_DONE)
                    }.build()
            )

            RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)

            Chip(
                onClick = {
                    launcher.launch(intent)
                },
                label = {
                    Text(
                        "Note header",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                secondaryLabel = {
                    Text(
                        text = notePreference ?: ""
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}