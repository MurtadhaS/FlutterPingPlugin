import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPingPlugin {
  static const MethodChannel _channel = const MethodChannel('flutter_ping_plugin');

  static Future<String> getPingResult(String url) async {
    final String version = await _channel.invokeMethod('getPingResult',{
			'url': url
		});
    print(version);
    return version;
  }
}
