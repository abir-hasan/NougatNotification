package com.abir.nougatnotification;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Abir Hasan on 18-Jan-17.
 */

public class BoundEventListener {


    private NougatNotification customNotification;

    public void onNotifyClick(View v) {
        Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        customNotification = new NougatNotification(v.getContext());

        customNotification.createNotification();
    }
}
