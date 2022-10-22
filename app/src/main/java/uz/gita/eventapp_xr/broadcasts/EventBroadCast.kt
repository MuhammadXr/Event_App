package uz.gita.eventapp_xr.broadcasts

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.speech.tts.TextToSpeech
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.eventapp_xr.data.room.dao.EventDao
import uz.gita.eventapp_xr.service.EventService
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EventBroadCast : BroadcastReceiver() {

    var tts: TextToSpeech? = null

    @Inject
    lateinit var dao: EventDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (tts == null){
            tts = TextToSpeech(context){ status ->
                if (status != TextToSpeech.ERROR){
                    tts!!.language = Locale.US
                }
            }
        }


        when(intent?.action){
            Intent.ACTION_SCREEN_ON -> {
                event(1)
            }
            Intent.ACTION_SCREEN_OFF -> {
                event(2)
            }
            Intent.ACTION_POWER_CONNECTED -> {
                event(15)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                event(16)
            }
            Intent.ACTION_TIME_CHANGED -> {
                event(11)
            }
            Intent.ACTION_BATTERY_LOW -> {
                event(14)
            }
            Intent.ACTION_BATTERY_OKAY -> {
                event(13)
            }
            Intent.ACTION_SHUTDOWN -> {
                event(12)
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirPlaneModeOn = intent.getBooleanExtra("state", false)
                if (isAirPlaneModeOn){
                    event(9)
                }
                else
                    event(10)
            }

            Intent.ACTION_HEADSET_PLUG -> {
                val state = intent.getIntExtra("state", -1)
                if (state == 0){
                    event(8)
                }
                else if (state == 1){
                    event(7)
                }
            }

            Intent.ACTION_REBOOT -> {
                context?.startService(Intent(context, EventService::class.java))
            }

            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val action = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                if (action?.isConnected!!){
                    event(3)
                }
                else
                    event(4)
            }

            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                if (state == BluetoothAdapter.STATE_OFF){
                    event(6)
                }
                else{
                    event(5)
                }
            }

        }
    }

    private fun event(id: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            val date = dao.getEventById(id)
            if (date.status == 1){
                speak(date.name)
            }
        }
    }

    private fun speak(text: String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "EVENT HAPPENED")
    }
}