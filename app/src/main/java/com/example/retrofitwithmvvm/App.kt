package com.example.retrofitwithmvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
//@HiltAndroidApp annotation, Hilt starts generating code in the background to perform DI to inject code in activity,
// fragment and so on..
class App:Application() {
}