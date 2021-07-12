package com.example.myapplication;


import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

class historyId {
    @Exclude
    public String historyId;

    public <T extends historyId> T withId(@NonNull final String id) {
        this.historyId = id;
        return (T) this;
    }
}
