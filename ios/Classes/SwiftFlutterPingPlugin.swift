import Flutter
import UIKit

public class SwiftFlutterPingPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_ping_plugin", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterPingPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    var arguments = call.arguments as? [String: String]

    // Ping once
    let once = SwiftyPing(host: arguments?["url"] as! String, configuration: PingConfiguration(interval: 0.5, with: 5), queue: DispatchQueue.global())
    once?.observer = { _, response in
      let duration = response.duration
      let b: String = String(format: "%f", duration)
      result(b)
      once?.stop()
    }
    once?.start()
  }
}
