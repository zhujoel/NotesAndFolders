package com.example.notesandfolders.presentation.helpers

import android.app.RemoteInput
import android.content.Intent
import android.view.inputmethod.EditorInfo
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.presentation.wearableExtender

fun getInputIntent(): Intent {
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