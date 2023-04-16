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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }

        context.startService(new Intent(context, BMSService.class));
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        boolean stepChargingManualOverride = (boolean) sharedPreferenceUtil.get(context, "bms_step_charging_switch",
                true);

        ShellUtils.execCommand("echo " + (stepChargingManualOverride ? "1" : "0") + " > /sys/class/power_supply/battery/step_charging_enabled", false);
        ShellUtils.execCommand("echo " + (stepChargingManualOverride ? "1" : "0") + " > /sys/class/power_supply/battery/sw_jeita_enabled", false);
    }
}
