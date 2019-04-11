import 'dart:async';
import 'package:flutter/services.dart';

class MediaDownloader {
  static const String _methodChannelName = 'io.yiss/media_downloader';
  static const String _invokedMethodName = 'getMediaFile';

  static const MethodChannel _channel = const MethodChannel(_methodChannelName);

  static const Map<MimeType, String> _mimeTypeMapper = {
    // MimeType Supported By Android For Video
    MimeType.VIDEO_M4V: 'video/mp4',
    MimeType.VIDEO_MP4: 'video/mp4',

    // MimeType Supported By Android For Image
    MimeType.IMAGE_BMP: 'image/x-ms-bmp',
    MimeType.IMAGE_GIF: 'image/gif',
    MimeType.IMAGE_JPEG: 'image/jpeg',
    MimeType.IMAGE_JPG: 'image/jpeg',
    MimeType.IMAGE_PNG: 'image/png',
  };

  static const Map<MimeType, String> _fileExtensionMapper = {
    // MimeType Supported By Android For Video
    MimeType.VIDEO_M4V: '.m4v',
    MimeType.VIDEO_MP4: '.mp4',

    // MimeType Supported By Android For Image
    MimeType.IMAGE_BMP: '.bmp',
    MimeType.IMAGE_GIF: '.gif',
    MimeType.IMAGE_JPEG: '.jpeg',
    MimeType.IMAGE_JPG: '.jpg',
    MimeType.IMAGE_PNG: '.png',
  };

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static void download(String url, MimeType mimeType,
      {String fileName, bool showNotification, String directory}) {
    Map<String, dynamic> arguments = Map();

    arguments['url'] = url;
    arguments['mimeType'] = _mimeTypeMapper[mimeType];
    arguments['fileName'] = fileName;
    arguments['fileExtension'] = _fileExtensionMapper[mimeType];
    arguments['showNotification'] = showNotification;
    arguments['directory'] = directory;

    _channel.invokeMethod(_invokedMethodName, arguments);
  }
}

enum MimeType {
  VIDEO_MP4,
  VIDEO_M4V,
  IMAGE_JPEG,
  IMAGE_PNG,
  IMAGE_BMP,
  IMAGE_GIF,
  IMAGE_JPG
}
