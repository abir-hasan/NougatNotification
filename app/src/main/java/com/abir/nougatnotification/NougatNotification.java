package com.abir.nougatnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;

import java.util.Calendar;

/**
 * Created by Abir Hasan on 18-Jan-17.
 */

public class NougatNotification {

    private Context context;
    private int mId = 1001;
    // Key for the string that's delivered in the action's intent.
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    public NougatNotification(Context context) {
        this.context = context;
    }

    public void createNotification() {

        int num = (int) Calendar.getInstance().getTimeInMillis();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //.setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        //.setAutoCancel(true)
                        //.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.artwork_1))
                        //.setLargeIcon(getBitmapFromVectorDrawable(context, R.drawable.notification_big_icon))
                        .setContentText("Hello World! " + num);


        //mBuilder.setStyle(createMessagingStyle());
        mBuilder.setDefaults(Notification.DEFAULT_ALL);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBuilder.setPriority(Notification.PRIORITY_HIGH);
        }
        mBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, FinalActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(FinalActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, num/*(int) Calendar.getInstance().getTimeInMillis()*/, resultIntent, 0);
        mBuilder.setContentIntent(resultPendingIntent);

        //mBuilder.addAction(addReplyAction());

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(num, mBuilder.build());
    }

    private NotificationCompat.MessagingStyle createMessagingStyle() {
        NotificationCompat.MessagingStyle messagingStyle = new
                NotificationCompat.MessagingStyle("");
        //messagingStyle.setConversationTitle("");

        //NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("Hello there! Welcome to connect!", System.currentTimeMillis(),"Abir");
        messagingStyle.addMessage("Hello there! Welcome to connect!", System.currentTimeMillis(), "1");
        messagingStyle.addMessage("Hello there! Welcome to connect!", System.currentTimeMillis(), "2");
        messagingStyle.addMessage("Hello there! Welcome to connect!", System.currentTimeMillis(), "3");
        messagingStyle.addMessage("Hello there! Welcome to connect!", System.currentTimeMillis(), "4");

        return messagingStyle;
    }

    private NotificationCompat.BigPictureStyle createBigPictureStyle() {
        NotificationCompat.BigPictureStyle bigPictureStyle = new
                NotificationCompat.BigPictureStyle();

        bigPictureStyle.setBigContentTitle("Abir Hasan");
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.artwork_1));
        bigPictureStyle.setSummaryText("Hello there! Welcome to connect!");

        return bigPictureStyle;
    }

    private NotificationCompat.Action addReplyAction() {
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Type here")
                .build();

        Intent intent = new Intent(context, ReplyIntentService.class);
        intent.putExtra("profile_id", "123456");
        intent.putExtra("sender_id", "654321");

        PendingIntent replyPendingIntent = PendingIntent.getService(context, (int) Calendar.getInstance().getTimeInMillis(),
                intent, 0);

        // Create the reply action and add the remote input.
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                        "Reply", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        return action;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
