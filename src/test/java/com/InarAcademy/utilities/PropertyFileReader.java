package com.InarAcademy.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    private static Properties configFile;

    public static void loadProperties(String filepath){
        String fullPath=System.getProperty("user.dir")+filepath;
        try {
            FileInputStream input=new FileInputStream(fullPath);
            configFile=new Properties();
            configFile.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getString(String key) {
        return configFile.getProperty(key);
    }

    public  static int getInt(String key){
        return Integer.parseInt(configFile.getProperty(key));
    }

    public static long getLong(String key) {
        return Long.parseLong(configFile.getProperty(key));
    }


    public static double getDouble(String key) {
        return Double.parseDouble(configFile.getProperty(key));
    }


    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(configFile.getProperty(key));
    }

    public static String getPropertyValue(String filePath, String key) throws IOException {
        loadProperties(filePath);
        return getString(key);
    }

}
