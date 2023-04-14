package com.example.notesandfolders.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.notesandfolders.R


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