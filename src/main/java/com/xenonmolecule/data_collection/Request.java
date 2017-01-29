package com.xenonmolecule.data_collection;

import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonElement.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Request {

    private HttpsURLConnection conn;

    public Request(String url) {
        try {
            URL siteURL = new URL(url);
            this.conn = (HttpsURLConnection) siteURL.openConnection();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getRawResponse() {
        if(conn!=null){
            try {
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                String input, output = "";

                while ((input = br.readLine()) != null)
                    output += input;

                br.close();
                return output;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getUrl() {
        return conn.getURL().toString();
    }


    private String requestWithParams(String ... params) {
        URL url = conn.getURL();
        String request = getUrl();
        if(params.length > 0) {
            request += ((url.getQuery() != null) ? "&" : "?") + params[0];
            for (int i = 1; i < params.length; i++) {
                request += "&" + params[i];
            }
        }
        return request;
    }

    // Literally just gets size parameter from json.
    // Super compelling method
    private int getSize(String jsonStr) {
        JsonElement json = new JsonParser().parse(jsonStr);
        JsonObject jObj = json.getAsJsonObject();
        return jObj.getAsJsonPrimitive("size").getAsInt();
    }

    // VexDB will sometimes only send a partially full
    // response, but by checking the size we can verify
    // that we are getting all of the data
    public int getResponseLength() {
        Request req = new Request(requestWithParams("nodata=true"));
        return getSize(req.getRawResponse());
    }

    public JsonArray getResults(String jsonStr) {
        JsonElement json = new JsonParser().parse(jsonStr);
        JsonObject jObj = json.getAsJsonObject();
        return jObj.getAsJsonArray("result");
    }

    // VexDB will sometimes only send a partially full
    // response, this guarantees that the response will
    // have everything
    public String getFullResponse() {
        String response = getRawResponse();
        int expectedResponseLength = getResponseLength();
        int responseLength = getSize(response);
        JsonArray results = getResults(response);
        while (expectedResponseLength != responseLength) {
            String req = new Request(requestWithParams("limit_start=" + responseLength)).getRawResponse();
            results.addAll(getResults(req));
            responseLength += getSize(req);
            // Get the json version of the response
            JsonElement json = new JsonParser().parse(response);
            JsonObject jObj = json.getAsJsonObject();
            // Update the result and size values
            jObj.remove("size");
            jObj.addProperty("size", responseLength);
            jObj.remove("result");
            jObj.add("result", results);
            // stringify the results again
            response = jObj.toString();
        }
        return response;
    }

    public JsonObject getFullResponseJSON() {
        return new JsonParser().parse(getFullResponse()).getAsJsonObject();
    }

}