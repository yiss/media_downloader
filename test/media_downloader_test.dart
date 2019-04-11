import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:media_downloader/media_downloader.dart';

void main() {
  const MethodChannel channel = MethodChannel('media_downloader');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await MediaDownloader.platformVersion, '42');
  });
}
