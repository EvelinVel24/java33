package main.java.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class issmonitoring {

    private static final String API_URL = "http://api.open-notify.org/iss-now.json";
    private JSONObject apiResponse;

    // 1. Establish API connection and return the API response object
    public JSONObject conexionApi() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        
        apiResponse = new JSONObject(content.toString());
        return apiResponse;
    }

    // 2. Get connection status
    public String statusConexion() {
        return apiResponse != null ? "success" : "failed";
    }

    // 3. Get connection ID (we'll simulate this part since the API doesn't provide it)
    public String idConexion() {
        return apiResponse.has("timestamp") ? apiResponse.get("timestamp").toString() : "unknown";
    }

    // 4. Get ISS Latitude
    public String infoLat() {
        return apiResponse.getJSONObject("iss_position").get("latitude").toString();
    }

    // 5. Get ISS Longitude
    public String infoLong() {
        return apiResponse.getJSONObject("iss_position").get("longitude").toString();
    }

    // Method to display the results
    public void printResults() {
        System.out.println("Status conexión a servidores de la ISS: " + statusConexion());
        System.out.println("ID de la conexión: " + idConexion());
        System.out.println("Latitud de la Estación Internacional: " + infoLat());
        System.out.println("Longitud de la Estación Internacional: " + infoLong());
    }

    public static void main(String[] args) {
        try {
            issmonitoring iss = new issmonitoring();
            iss.conexionApi();
            iss.printResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
