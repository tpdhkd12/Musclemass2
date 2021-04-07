package com.example.musclemass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestHttpURLConnection {

    public String request(String _url){

        HttpURLConnection urlConnection = null;

        try{
            URL url = new URL(_url);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept-Charset","utf-8");
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");


            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

            String line;
            String page = "";
            while ((line = reader.readLine()) != null){
                page += line;
            }
            return page;




        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e ){
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return null;



    }
}
