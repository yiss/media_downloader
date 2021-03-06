package io.yiss.mediadownloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.File


class MediaDownloaderPlugin: MethodCallHandler {



  companion object {
      private lateinit var mediaDownloader: MediaDownloader
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "io.yiss/media_downloader")
      this.mediaDownloader = MediaDownloader(registrar.context())
        channel.setMethodCallHandler(MediaDownloaderPlugin())

    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getMediaFile") {
        val url = call.argument<String>("url")!!
        val mimeType = call.argument<String>("mimeType")!!
        val fileExtension = call.argument<String>("fileExtension")
        val fileName = call.argument<String>("fileName")
        val showNotification = call.argument<Boolean>("fileName")
        val directory = call.argument<String>("directory")
        mediaDownloader.download(url, mimeType, fileExtension, fileName,showNotification, directory)
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }
}


class MediaDownloader(context: Context) {

    private val context = context

    companion object {
        const val LOG_TAG = "MEDIA_DOWNLOADER"
    }

    fun download(url: String, mimeType: String, fileExtension: String?, fileName: String?, showNotification: Boolean?, directory: String?) {
        val separator = File.separator

        val storageDirectoryPath = if (directory != null) {
            // In case we want to store the files in a separate directory
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath}$separator$directory$separator"
        } else {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        }

        val storageDirectory = File(storageDirectoryPath)

        if (!storageDirectory.exists()) {
            storageDirectory.mkdir()
            Log.d(LOG_TAG, "Folder ${storageDirectory.absolutePath} was created for the first time")
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)

        val request = DownloadManager.Request(uri)


        val showNotificationFlag = showNotification ?: false

        val notificationVisibility = if (showNotificationFlag) DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED else DownloadManager.Request.VISIBILITY_HIDDEN

        val altFileName = fileName ?: System.nanoTime().toString()

        val filePath = "$storageDirectoryPath$separator$altFileName$fileExtension"

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setMimeType(mimeType)
                .setNotificationVisibility(notificationVisibility)
                .setTitle(altFileName)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, filePath)

        downloadManager.enqueue(request)
    }

}