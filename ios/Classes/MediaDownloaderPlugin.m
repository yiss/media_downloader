#import "MediaDownloaderPlugin.h"
#import <media_downloader/media_downloader-Swift.h>

@implementation MediaDownloaderPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMediaDownloaderPlugin registerWithRegistrar:registrar];
}
@end
