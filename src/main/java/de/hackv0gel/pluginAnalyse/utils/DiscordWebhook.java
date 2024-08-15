package de.hackv0gel.pluginAnalyse.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordWebhook {

    public static void send(String webhookUrl, String message) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = "{\"content\":\"" + message + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 204) {
                System.out.println("Discord Webhook response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
