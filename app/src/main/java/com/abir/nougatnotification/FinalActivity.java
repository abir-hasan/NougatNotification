package com.abir.nougatnotification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Abir Hasan on 18-Jan-17.
 */

public class FinalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, getMessageText(getIntent()), Toast.LENGTH_SHORT).show();

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
