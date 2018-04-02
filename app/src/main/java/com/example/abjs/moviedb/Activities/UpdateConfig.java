package com.example.abjs.moviedb.Activities;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.abjs.moviedb.Model.configValues;
import com.example.abjs.moviedb.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import static android.content.ContentValues.TAG;

public class UpdateConfig {

    public boolean getConfigUpdate(){
        Properties prop = new Properties();
        OutputStream output= null;
        boolean update =false;

        try{
            output = new FileOutputStream("config.properties");

            prop.setProperty("URL","https://api.themoviedb.org/3/");
            prop.setProperty("Key","75d6a21a24cc88a4b60fcd0246f02ad1");

            prop.store(output,null);
            System.out.println(" configuration done");

            update= true;
        }catch(Exception e){

            e.printStackTrace();
            update= false;
        } finally
        {
            if (output != null)
            {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       return update;
    }

    void readConfig(){

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("Key"));
            configValues.Key = prop.getProperty("Key");
            System.out.println(prop.getProperty("URL"));
            configValues.Url = prop.getProperty("URl");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

        public static String getConfigValue(Context context, String name) {
            Resources resources = context.getResources();

            try {
                InputStream rawResource = resources.openRawResource(R.raw.config);
                Properties properties = new Properties();
                properties.load(rawResource);
                return properties.getProperty(name);

            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Unable to find the config file: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "Failed to open config file.");
            }

            return null;
        }
    }

