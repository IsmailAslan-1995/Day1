package com.InarAcademy.utilities;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {
        String path=System.getProperty("user.dir")+"/configuration.properties";
        try {
            FileInputStream input=new FileInputStream(path);
            configFile=new Properties();
            configFile.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("File path "+path+" is not correct");
        }
    }
    public static  String getProperty(String property){
        return configFile.getProperty(property);
    }
}
