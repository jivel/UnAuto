/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.jimenezlav.unauto;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.ParseAnalytics;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}
