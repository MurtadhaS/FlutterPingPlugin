package io.mantissa.flutter_ping_plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterPingPlugin
 */
public class FlutterPingPlugin implements MethodCallHandler {
	/**
	 * Plugin registration.
	 */
	public static void registerWith(Registrar registrar) {
		final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_ping_plugin");
		channel.setMethodCallHandler(new FlutterPingPlugin());
	}

	@Override
	public void onMethodCall(MethodCall call, Result result) {
		if (call.method.equals("getPlatformVersion")) {
			result.success("Android " + android.os.Build.VERSION.RELEASE);
		} else if (call.method.equals("getPingResult")) {
			String url = call.argument("url");
			String resultString = executeCmd("ping -c 5 -w 5 " + url, false);
			  result.success(resultString);
		}
	}


	static String executeCmd(String cmd, boolean sudo) {
		try {
			Process process;
			if (!sudo)
				process = Runtime.getRuntime().exec(cmd);
			else {
				process = Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
			}
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String string;
			String response = "";
			while ((string = stdInput.readLine()) != null) {
				response += string + "\n";
			}
			process.destroy();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
}
