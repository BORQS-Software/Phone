/*
 * Copyright (C) 2011 Borqs Inc.
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
package com.android.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Config;
import android.util.Log;


public class VideoCallProxy extends BroadcastReceiver {

	final String ACTION_NOTIFY_MISSED_CALL = "com.borqs.videocall.action.NotifyMissedCall";
	
	public synchronized void onReceive(Context context, Intent intent) {
		log("onReceive");
		String action = intent.getAction();

        if (action.equals(ACTION_NOTIFY_MISSED_CALL)) {
            String name = intent.getStringExtra("name");
            String number = intent.getStringExtra("number");
            String label = intent.getStringExtra("label");
            long date = intent.getLongExtra("date", 0);
            log("Missed video call from name: " + name +
            		", number: " + number + ", label: " + label+", date: " + date);
            PhoneApp.getInstance().notificationMgr.notifyMissedVideoCall(name, number, label, date);
		}
	}
	private void log(String msg) {
		if (/*PhoneApp.isPhoneLogEnabled &&*/ Config.DEBUG) {
			Log.d("VideoCallProxy", msg);
		}
	}    
}

