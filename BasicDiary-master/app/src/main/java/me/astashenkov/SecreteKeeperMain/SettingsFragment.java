package me.astashenkov.SecreteKeeperMain;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import me.astashenkov.basicdiary.R;


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String s) {

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
