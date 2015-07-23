package com.framgia.lupx.androidtraining.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.activities.NotificationReceiverActivity;


/**
 * Created by FRAMGIA\pham.xuan.lu on 21/07/2015.
 */
public class NotificationFragment extends Fragment {

    public final static int NOTIFICATION_ID = 1;
    private View sendNotification;
    private EditText edtName;
    private EditText edtMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        edtName = (EditText) view.findViewById(R.id.txtName);
        edtMessage = (EditText) view.findViewById(R.id.txtMessage);
        sendNotification = view.findViewById(R.id.btnSend);
        sendNotification.setOnClickListener(onClickListener);
        return view;
    }

    private void sendNotification() {

        String name = edtName.getText().toString();
        String message = edtMessage.getText().toString();

        Intent intent = new Intent(getActivity(), NotificationReceiverActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(NotificationReceiverActivity.class);
        stackBuilder.addNextIntent(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("NAME", name);
        intent.putExtra("MESSAGE", message);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.megaphone)
                .setContentTitle("Received new message from " + name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .setVibrate(new long[]{500, 200})
                .setContentText(message).setLights(Color.GREEN, 300, 300);


        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, nBuilder.build());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSend:
                    sendNotification();
                    break;
            }
        }
    };
}
