package com.kanzy.music.extension

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSubmit(code: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_NONE,
            EditorInfo.IME_ACTION_GO,
            EditorInfo.IME_ACTION_SEARCH,
            EditorInfo.IME_ACTION_SEND,
            EditorInfo.IME_ACTION_NEXT -> code()
        }
        clearFocus()
        true
    }
}