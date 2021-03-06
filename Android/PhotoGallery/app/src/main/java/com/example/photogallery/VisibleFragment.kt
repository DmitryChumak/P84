package com.example.photogallery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class VisibleFragment : Fragment() {
    private val onShowNotification = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(requireContext(), "Got a broadcast: ${p1?.action}", Toast.LENGTH_LONG).show()
        }

    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(PollWorker.ACTION_SHOW_NOTIFICATION)
        requireActivity().registerReceiver(
            onShowNotification,
            filter,
            PollWorker.PERM_PRIVATE,
            null
        )
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(onShowNotification)
    }
}