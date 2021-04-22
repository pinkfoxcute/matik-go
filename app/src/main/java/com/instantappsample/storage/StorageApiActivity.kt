/*
 * Copyright (C) 2019 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.instantappsample.storage

import android.os.Build
import android.os.Bundle
import android.os.ParcelFileDescriptor.AutoCloseInputStream
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.instantapps.InstantApps
import java.util.zip.ZipInputStream

/**
 * A basic Activity that shows usage of the Storage API,
 * [documented here](https://developers.google.com/android/reference/com/google/android/gms/instantapps/InstantAppsClient#getInstantAppData()).
 *
 * This sample does not deal with actual data.
 * A developer working with this API will have to implement `readStoredData(data)`.
 */
class StorageApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        @Suppress("UsePropertyAccessSyntax")
        findViewById<TextView>(R.id.context_view)
            .setText(
                getString(
                    R.string.context_pretext,
                    getString(R.string.app_context)
                )
            )
        migrateDataFromInstantApp()
    }

    /**
     * Migrates data from the Instant App.
     */
    private fun migrateDataFromInstantApp() {
        // Only runs on API levels < 26.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Only runs in an Installed App.
            if (!InstantApps.getPackageManagerCompat(this).isInstantApp) {
                InstantApps.getInstantAppsClient(this).instantAppData
                    .addOnSuccessListener { dataZip ->
                        val data = ZipInputStream(AutoCloseInputStream(dataZip))
                        readStoredData(data)
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "Something broke ${it.message}")
                        TODO("Don't just log this in a production app.")
                    }
            }
        } else {
            Toast.makeText(
                this, "This API is not intended to be used on Android O and above.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Implement this method to retrieve the data stored within the Instant App.
     */
    private fun readStoredData(data: ZipInputStream) {
        Toast.makeText(
            this, "Stored data can be read. See source code for more info.",
            Toast.LENGTH_LONG
        ).show()
        /*
        TODO("Read the stored data, if necessary parse them " +
                "and store the results in the installed app.")
        */
    }

    companion object {
        private val TAG = "StorageApiActivity"
    }
}
