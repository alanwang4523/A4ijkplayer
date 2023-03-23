/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
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

package com.alanwang4523.a4ijkplayerdemo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.alanwang4523.a4ijkplayerdemo.R;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static SettingsFragment newInstance() {
        SettingsFragment f = new SettingsFragment();
        return f;
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings);
    }


    private void setAllPreferencesToAvoidHavingExtraSpace(Preference preference) {
        preference.setIconSpaceReserved(false);
        if (preference instanceof PreferenceGroup)
            for(int i=0;i<((PreferenceGroup) preference).getPreferenceCount();i++){
                setAllPreferencesToAvoidHavingExtraSpace(((PreferenceGroup) preference).getPreference(i));
            }
    }

    @Override
    public void  setPreferenceScreen(PreferenceScreen preferenceScreen) {
        if (preferenceScreen != null)
            setAllPreferencesToAvoidHavingExtraSpace(preferenceScreen);
        super.setPreferenceScreen(preferenceScreen);

    }

    @SuppressLint("RestrictedApi")
    @Override
    protected RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen){
            @Override
            public void onPreferenceHierarchyChange(Preference preference) {
                if(null!=preference){
                    setAllPreferencesToAvoidHavingExtraSpace(preference);
                }
                super.onPreferenceHierarchyChange(preference);
            }
        };
    }
}
