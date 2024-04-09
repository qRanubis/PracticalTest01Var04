package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class PracticalTest01Var04Service extends Service {
    private final Handler handler = new Handler();
    private Runnable sendMessageRunnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        String group = intent.getStringExtra("group");

        sendMessageRunnable = new Runnable() {
            @Override
            public void run() {
                Intent nameIntent = new Intent("ro.pub.cs.systems.eim.practicaltest01var04.name");
                nameIntent.putExtra("name", name);
                sendBroadcast(nameIntent);

                Intent groupIntent = new Intent("ro.pub.cs.systems.eim.practicaltest01var04.group");
                groupIntent.putExtra("group", group);
                sendBroadcast(groupIntent);

                handler.postDelayed(this, 5000);
            }
        };

        handler.post(sendMessageRunnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendMessageRunnable);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
