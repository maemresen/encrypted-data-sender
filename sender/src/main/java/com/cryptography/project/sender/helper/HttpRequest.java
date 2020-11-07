package com.cryptography.project.sender.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emre Sen
 * @date Dec 26, 2018
 * @contact maemresen07@gmail.com
 */
public class HttpRequest {

    private static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    private static String doHttpRequest(String requestUrl, Map<String, String> requestParams, String requestMethod) throws MalformedURLException, ProtocolException, IOException {

        URL url = new URL(requestUrl);
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.setRequestMethod(requestMethod);
        httpUrlConnection.setConnectTimeout(5000);
        httpUrlConnection.setReadTimeout(5000);
        httpUrlConnection.setRequestProperty("Accept", "application/json");

        
        if (!requestParams.isEmpty()) {
            httpUrlConnection.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(httpUrlConnection.getOutputStream())) {
                String queryParams = getParamsString(requestParams);
                out.writeBytes(queryParams);
                out.flush();
            }
        }

        httpUrlConnection.getInputStream();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        }
    }

    private static String doHttpRequest(String requestUrl, String requestMethod) throws IOException {
        return doHttpRequest(requestUrl, new HashMap<>(), requestMethod);
    }

    /**/
    public static String doGet(String requestUrl, Map<String, String> requestParams) throws IOException {
        return doHttpRequest(requestUrl, requestParams, "GET");
    }

    public static String doGet(String requestUrl) throws IOException {
        return doHttpRequest(requestUrl, "GET");
    }

    /**/
    public static String doPost(String requestUrl, Map<String, String> requestParams) throws IOException {
        return doHttpRequest(requestUrl, "POST");
    }

    public static String doPost(String requestUrl) throws IOException {
        return doHttpRequest(requestUrl, "POST");
    }
}
