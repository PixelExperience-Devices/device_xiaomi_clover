/*
 * Copyright (C) 2018-2019 The Xiaomi-SDM660 Project
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

package org.lineageos.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings;

import org.lineageos.settings.device.kcal.Utils;
import org.lineageos.settings.device.preferences.SecureSettingSwitchPreference;

import java.lang.Math.*;
import java.util.Locale;

public class BootReceiver extends BroadcastReceiver implements Utils {

    public static final  String EARPIECE_GAIN_PATH = "/sys/kernel/sound_control/earpiece_gain";
    public static final  String HEADPHONE_GAIN_PATH = "/sys/kernel/sound_control/headphone_gain";
    public static final  String MIC_GAIN_PATH = "/sys/kernel/sound_control/mic_gain";
    
    public void onReceive(Context context, Intent intent) {

        // KCAL
        if (Settings.Secure.getInt(context.getContentResolver(), PREF_ENABLED, 0) == 1) {
            FileUtils.setValue(KCAL_ENABLE, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_ENABLED, 0));

            String rgbValue = Settings.Secure.getInt(context.getContentResolver(),
                    PREF_RED, RED_DEFAULT) + " " +
                    Settings.Secure.getInt(context.getContentResolver(), PREF_GREEN,
                            GREEN_DEFAULT) + " " +
                    Settings.Secure.getInt(context.getContentResolver(), PREF_BLUE,
                            BLUE_DEFAULT);

            FileUtils.setValue(KCAL_RGB, rgbValue);
            FileUtils.setValue(KCAL_MIN, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_MINIMUM, MINIMUM_DEFAULT));
            FileUtils.setValue(KCAL_SAT, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_GRAYSCALE, 0) == 1 ? 128 :
                    Settings.Secure.getInt(context.getContentResolver(),
                            PREF_SATURATION, SATURATION_DEFAULT) + SATURATION_OFFSET);
            FileUtils.setValue(KCAL_VAL, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_VALUE, VALUE_DEFAULT) + VALUE_OFFSET);
            FileUtils.setValue(KCAL_CONT, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_CONTRAST, CONTRAST_DEFAULT) + CONTRAST_OFFSET);
            FileUtils.setValue(KCAL_HUE, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_HUE, HUE_DEFAULT));
        }
        
        // Audio Gain
        int gain = Settings.Secure.getInt(context.getContentResolver(),
                DeviceSettings.PREF_HEADPHONE_GAIN, 0);
        FileUtils.setValue(HEADPHONE_GAIN_PATH, gain + " " + gain);
        FileUtils.setValue(MIC_GAIN_PATH, Settings.Secure.getInt(context.getContentResolver(),
                DeviceSettings.PREF_MIC_GAIN, 0));
        FileUtils.setValue(EARPIECE_GAIN_PATH, Settings.Secure.getInt(context.getContentResolver(),
                DeviceSettings.PREF_EARPIECE_GAIN, 0));

        // Notification LED
        FileUtils.setValue(DeviceSettings.NOTIF_LED_PATH,(1 + Math.pow(1.05694, Settings.Secure.getInt(
                context.getContentResolver(), DeviceSettings.PREF_NOTIF_LED, 100))));

        // Vibration Strength
        FileUtils.setValue(DeviceSettings.VIBRATION_STRENGTH_PATH, Settings.Secure.getInt(
                context.getContentResolver(), DeviceSettings.PREF_VIBRATION_STRENGTH, 80) / 100.0 * (DeviceSettings.MAX_VIBRATION - DeviceSettings.MIN_VIBRATION) + DeviceSettings.MIN_VIBRATION);
        FileUtils.setValue(DeviceSettings.HALL_WAKEUP_PATH, Settings.Secure.getInt(
                context.getContentResolver(), DeviceSettings.PREF_HALL_WAKEUP, 1) == 1 ? "Y" : "N");
        FileUtils.setProp(DeviceSettings.HALL_WAKEUP_PROP, Settings.Secure.getInt(
                context.getContentResolver(), DeviceSettings.PREF_HALL_WAKEUP, 1) == 1);

        // Dirac
        context.startService(new Intent(context, DiracService.class));

        // USB Fastcharge
        FileUtils.setValue(DeviceSettings.USB_FASTCHARGE_PATH, Settings.Secure.getInt(context.getContentResolver(),
                DeviceSettings.PREF_USB_FASTCHARGE, 0));

        // FPS Info
        boolean enabled = Settings.Secure.getInt(context.getContentResolver(), 
                DeviceSettings.PREF_KEY_FPS_INFO, 0) == 1;
        if (enabled) {
            context.startService(new Intent(context, FPSInfoService.class));
        }

        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance();
            boolean first_ref_shown = (boolean) sharedPreferenceUtil.get(context, "first_ref_shown",
                    false);

            if(!first_ref_shown) {
                Locale locale = Resources.getSystem().getConfiguration().getLocales().get(0);
                String tag = locale.toLanguageTag();
                if(tag.equals("zh-Hans-CN")) {
                    Intent intent1 = new Intent(context, NoticeActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }
            }
            
            boolean update_2023_07_shown = (boolean) sharedPreferenceUtil.get(context, "update_2023_07_shown",
                    true);

            if(!update_2023_07_shown) {
                Intent intent2 = new Intent(context, Notice202307UpdateActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
        }
    }
}
