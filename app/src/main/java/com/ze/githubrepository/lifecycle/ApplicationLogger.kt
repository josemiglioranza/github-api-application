package com.ze.githubrepository.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class ApplicationLogger : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_CREATE -> Log.d("Criou Activity", "Nossa activity foi criada")
            Lifecycle.Event.ON_RESUME -> Log.d("Continuou Activity", "Nossa activity foi continuada")
            Lifecycle.Event.ON_STOP -> Log.d("Parou Activity", "Nossa activity foi parada")
        }
    }
}