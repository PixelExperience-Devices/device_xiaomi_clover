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

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
import com.android.settingslib.widget.R;

public class BMSActivity extends CollapsingToolbarBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Fragment fragment = getFragmentManager().findFragmentById(R.id.content_frame);
        BMSFragment bmsFragment;
        if (fragment == null) {
            bmsFragment = new BMSFragment();
            getFragmentManager().beginTransaction().add(R.id.content_frame, bmsFragment).commit();
        }
    }
}