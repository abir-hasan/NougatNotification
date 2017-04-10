package com.abir.nougatnotification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.util.Log;

/**
 * Created by Abir Hasan on 19-Jan-17.
 */

public class ReplyIntentService extends IntentService {

    private static final String TAG = "ReplyIntentService";

    public ReplyIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.w(TAG, "onHandleIntent() Replied Message: " + getMessageText(intent));
        if (intent.hasExtra("profile_id")) {
            Log.w(TAG, "onHandleIntent() profile_id: " + intent.getStringExtra("profile_id"));
        }

        if (intent.hasExtra("sender_id")) {
            Log.w(TAG, "onHandleIntent() sender_id: " + intent.getStringExtra("sender_id"));
        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Dismissing the current notification
            mNotificationManager.cancel(1001);


            return remoteInput.getCharSequence(NougatNotification.KEY_TEXT_REPLY);
        }
        return null;
    }
}
