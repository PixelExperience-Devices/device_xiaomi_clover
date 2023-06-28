/*
 * Copyright (C) 2018-2020 The Xiaomi-SDM660 Project
 *
 *  https://github.com/xiaomi-sdm660
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
 * limitations under the License
 */

package com.advanced.settings.doze;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import android.provider.Settings;
import android.util.Log;

import static android.provider.Settings.Secure.DOZE_ENABLED;

public final class Utils {

    private static final String TAG = "DozeUtils";
    private static final boolean DEBUG = false;

    private static final String DOZE_INTENT = "com.android.systemui.doze.pulse";

    protected static final String DOZE_ENABLE = "doze_enable";
    protected static final String CATEG_PROX_SENSOR = "proximity_sensor";
    protected static final String CATEG_TILT_SENSOR = "tilt_sensor";

    protected static final String GESTURE_PICK_UP_KEY = "gesture_pick_up";
    protected static final String GESTURE_RAISE_TO_WAKE_KEY = "gesture_raise_to_wake";
    protected static final String GESTURE_HAND_WAVE_KEY = "gesture_hand_wave";
    protected static final String GESTURE_POCKET_KEY = "gesture_pocket";

    protected static void startService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting service");
        context.startServiceAsUser(new Intent(context, DozeService.class),
                UserHandle.CURRENT);
    }

    protected static void stopService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping service");
        context.stopServiceAsUser(new Intent(context, DozeService.class),
                UserHandle.CURRENT);
    }

    protected static void checkDozeService(Context context) {
        if (isDozeEnabled(context) && sensorsEnabled(context)) {
            startService(context);
        } else {
            stopService(context);
        }
    }

    protected static boolean getProxCheckBeforePulse(Context context) {
        try {
            Context con = context.createPackageContext("com.android.systemui", 0);
            int id = con.getResources().getIdentifier("doze_proximity_check_before_pulse",
                    "bool", "com.android.systemui");
            return con.getResources().getBoolean(id);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    protected static boolean isDozeEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(),
                DOZE_ENABLED, 1) != 0;
    }

    protected static boolean enableDoze(Context context, boolean enable) {
        return Settings.Secure.putInt(context.getContentResolver(),
                DOZE_ENABLED, enable ? 1 : 0);
    }

    protected static void launchDozePulse(Context context) {
        if (DEBUG) Log.d(TAG, "Launch doze pulse");
        context.sendBroadcastAsUser(new Intent(DOZE_INTENT),
                new UserHandle(UserHandle.USER_CURRENT));
    }

    protected static void enableGesture(Context context, String gesture, boolean enable) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(gesture, enable).apply();
    }

    protected static boolean isGestureEnabled(Context context, String gesture) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(gesture, false);
    }

    protected static boolean isPickUpEnabled(Context context) {
        return isGestureEnabled(context, GESTURE_PICK_UP_KEY);
    }
    
    protected static void setPickUp(Preference preference, boolean value) {
        SwitchPreference pickup = (SwitchPreference)preference;
        pickup.setChecked(value);
        pickup.setEnabled(!value);
    }
    
    protected static boolean isRaiseToWakeEnabled(Context context) {
        return isGestureEnabled(context, GESTURE_RAISE_TO_WAKE_KEY);
    }

    protected static boolean isHandwaveGestureEnabled(Context context) {
        return false; //isGestureEnabled(context, GESTURE_HAND_WAVE_KEY);
    }

    protected static boolean isPocketGestureEnabled(Context context) {
        return false; //isGestureEnabled(context, GESTURE_POCKET_KEY);
    }

    protected static boolean sensorsEnabled(Context context) {
        return isPickUpEnabled(context) || isHandwaveGestureEnabled(context)
                || isPocketGestureEnabled(context) || isRaiseToWakeEnabled(context);
    }

}
