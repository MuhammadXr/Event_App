package uz.gita.eventapp_xr.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventapp_xr.MainActivity
import uz.gita.eventapp_xr.broadcasts.EventBroadCast


@AndroidEntryPoint
class EventService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private var eventBroadCast: EventBroadCast? = null

    override fun onCreate() {
        super.onCreate()

        if (eventBroadCast == null){
            eventBroadCast = EventBroadCast()
        }

        createChannel()

        val id = 123
        val notification = NotificationCompat.Builder(this, "Event")
            .setContentTitle("EVENT APP")
            .setContentText("EVENT APP listening events now")
            .setContentIntent(
                PendingIntent.getActivity(
                    this@EventService,
                    0,
                    Intent(this@EventService, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )
            ).build()

        startForeground(id, notification)

        registerReceiver(eventBroadCast, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_REBOOT)
        })
    }

    private fun createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "Event",
                "Main",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(notificationChannel)
        }

    }
}