#import "FlutterPingPlugin.h"
#import <flutter_ping_plugin/flutter_ping_plugin-Swift.h>

@implementation FlutterPingPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterPingPlugin registerWithRegistrar:registrar];
}
@end
