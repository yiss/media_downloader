# media_dowonloader

A flutter plugin to dowonlaod media (Videos, Images) from the network and save them to the gallery. Supports only Android.

## Usage

To use the plugin, add `media_downloader` as [dependency in your pubspec.yaml file.](https://flutter.dev/docs/development/packages-and-plugins/using-packages "dependency in your pubspec.yaml file.")

You also need to ask for the permission to use the external storage (you can use something like : )

And add this to your AndroidManifest.xml :

` <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>`

Also if you want to not show donwload notification you need to add :
`<uses-permission
        android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />`

### Example :

```dart
void download() {
	MediaDownloader.download('https://cdn-images-1.medium.com/max/800/0*akL0KXb54mViVajR.', MimeType.IMAGE_GIF)
}
```

By default the Media Donwloaer plugin donwload the image to the external storage and save with correspondant timestamp as file name.

## Arguments :

|  Argument Name | Default Value   | Description  |
| ------------ | ------------ | ------------ |
| url | Mandatory (no default value)  |  The url of the medial you would like to download  |
| mimeType | Mandaotry (no default value) see below for options | The type of the media you would like to download |
| fileName | The timestamp of the file | The name of the file you would like to download |
| showNotification | Shows only a notification after the download is completed (true) | To hide the after download notification |
| direcotry | The root of the external storage | (String)  |

### Example :
To Do

