package com.api.TestBase;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

    public int RESPONSE_STATUS_CODE_200 =200;
    public int RESPONSE_STATUS_CODE_400 =400;
    public int RESPONSE_STATUS_CODE_500 =500;
    public int RESPONSE_STATUS_CODE_401 =401;
    public int RESPONSE_STATUS_CODE_201 =201;


    public Properties prop;
    public TestBase()
    {
        try{


            prop=new Properties();
            FileInputStream ip = new FileInputStream((".\\src\\main\\java\\com\\api\\config\\config.properties"));

            prop.load(ip);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
