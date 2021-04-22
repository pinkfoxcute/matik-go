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

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StorageApiActivityTest {

    @JvmField
    @Rule val testRule = object : ActivityTestRule<StorageApiActivity>(
        StorageApiActivity::class.java,
        true
    ) {
        override fun getActivityIntent(): Intent {
            return Intent()
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://storage-api.instantappsample.com/"))
        }
    }

    /**
     * Tests whether the Activity can be launched via its registered URL.
     */
    @Test
    fun isAddressableViaUrl() {
        onView(withText(R.string.usage)).check(matches(isDisplayed()))
    }
}
