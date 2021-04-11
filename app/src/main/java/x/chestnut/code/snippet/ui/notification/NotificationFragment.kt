package x.chestnut.code.snippet.ui.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment


/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/12/12 15:50
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class NotificationFragment : ScrollBaseFragment() {

    companion object {
        private const val CHANNEL_ID = "theChannelId"
        private const val NORMAL_CHANNEL_ID = "NORMAL_CHANNEL_ID"
        private const val IMPORTANT_CHANNEL_ID = "IMPORTANT_CHANNEL_ID"
    }

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        addView("注册 Channel 到系统") {
            val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, "非常重要的消息",
                        NotificationManager.IMPORTANCE_HIGH)
                channel.description = "好鬼死重要噶通知啊，墙裂建议开启开启呀~"
                notificationManager.createNotificationChannel(channel)
            }
        }
        addView("获取 Channel 的重要程度") {
            val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = notificationManager.getNotificationChannel(CHANNEL_ID)
                channel?.let {
                    val importance = it.importance
                    Toast.makeText(context, "importance: $importance", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }
        addView("引导用户修改通知Channel") {
            context?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, it.packageName)
                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID)
                    it.startActivity(intent)
                }
            }
        }
        addView("ChannelGroup") {
            context?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createAllNotificationChannel(it, "1", "张三")
                    createAllNotificationChannel(it, "2", "李四")
                }
            }
        }
        addView("手动修改不同Group下的同一个Channel为不同的重要程度") {
            val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val notificationChannelGroup = notificationManager.getNotificationChannelGroup("1")
                    val channels = notificationChannelGroup.channels
                    for (channel in channels) {
                        Log.d("chestnut", "chanel: $channel")
                    }
                }
            }
        }
        addView("发通知！") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context?.let {
                    val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE)
                            as NotificationManager
                    val notification = NotificationCompat.Builder(it, CHANNEL_ID)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                            .setContentIntent(null)
                            .setContentTitle("重要消息")
                            .setContentText("今晚去约Pao")
                            .build()
                    notificationManager.notify(9527, notification)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAllNotificationChannel(context: Context, groupId: String, groupName: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannelGroup(NotificationChannelGroup(groupId, groupName))
        val channel1 = NotificationChannel(NORMAL_CHANNEL_ID + groupId,
                "普通通知", NotificationManager.IMPORTANCE_LOW)
        channel1.description = "这是普通通知，不太重要"
        channel1.group = groupId
        val channel2 = NotificationChannel(IMPORTANT_CHANNEL_ID + groupId,
                "重要通知", NotificationManager.IMPORTANCE_HIGH)
        channel2.description = "这是重要通知，建议开启"
        channel2.group = groupId
        val channels: MutableList<NotificationChannel> = ArrayList()
        channels.add(channel1)
        channels.add(channel2)
        manager.createNotificationChannels(channels)
    }
}