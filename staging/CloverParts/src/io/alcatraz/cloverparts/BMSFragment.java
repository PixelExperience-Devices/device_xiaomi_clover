/*
 * Copyright (c) 2023, Alcatraz323 <alcatraz32323@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package io.alcatraz.cloverparts;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import static io.alcatraz.cloverparts.Constants.BMS_STEP_CHG_SWITCH;
import static io.alcatraz.cloverparts.Constants.BMS_ALWAYS_CONNECTED_MODE;
import static io.alcatraz.cloverparts.Constants.BMS_LIMIT_TO_EIGHTY;

public class BMSFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SwitchPreference mStepChargingSwitch;

    @Override
    public void onCreatePreferences(Bundle bundle, String key) {
        addPreferencesFromResource(R.xml.bms_settings);
        findPreferences();
        bindListeners();
        updateSwitches();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch (preference.getKey()) {
            case BMS_STEP_CHG_SWITCH:
                boolean enabled = (boolean) o;
                ShellUtils.execCommand("echo " + (enabled ? "1" : "0") + " > /sys/class/power_supply/battery/step_charging_enabled", false);
                ShellUtils.execCommand("echo " + (enabled ? "1" : "0") + " > /sys/class/power_supply/battery/sw_jeita_enabled", false);
                break;
        }
        return true;
    }

    private void findPreferences() {
        mStepChargingSwitch = findPreference(BMS_STEP_CHG_SWITCH);
    }

    private void bindListeners() {
        mStepChargingSwitch.setOnPreferenceChangeListener(this);
    }

    private void updateSwitches() {
        ShellUtils.CommandResult result = ShellUtils.execCommand("cat /sys/class/power_supply/battery/step_charging_enabled", false);
        mStepChargingSwitch.setChecked(result.responseMsg.contains("1"));
    }
}
