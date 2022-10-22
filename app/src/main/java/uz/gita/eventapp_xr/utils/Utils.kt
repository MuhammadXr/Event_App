package uz.gita.eventapp_xr.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import uz.gita.eventapp_xr.MainActivity


object Utils {
    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=uz.gita.eventapp_xr")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.gita.eventapp_xr")
                )
            )
        }
    }

    fun share(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Event App")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "link: https://play.google.com/store/apps/details?id=uz.gita.eventapp_xr"
        )
        context.startActivity(Intent.createChooser(intent, "Event App"))
    }
}