package edu.kirkwood.shared;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kirkwood.demos.ArtistFromJson;
import edu.kirkwood.finalproject.models.JsonModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONException;
import org.json.JSONObject;

import static edu.kirkwood.demos.JsonReader.readAll;

public class EmailVerification {
    public static boolean VerifyEmail(String email) {

        try {

            Dotenv dotenv = Dotenv.load();
            String key = dotenv.get("API_KEY");

            JSONObject obj = readJsonFromUrl("https://emailvalidation.abstractapi.com/v1/?api_key=" + key + "&auto_correct=false&email="+ email);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonModel json = mapper.readValue(obj.toString(), JsonModel.class);
            if (json.deliverability.equals("DELIVERABLE")) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception error) { return false; }
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

