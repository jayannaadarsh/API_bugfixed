import com.api.TestBase.TestBase;
import com.api.client.RestClient;
import com.api.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {
    TestBase testbase;
    String URL;
    RestClient restclient;


    @BeforeMethod
    public void SetUp() throws Exception
    {
        testbase= new TestBase();
        URL=prop.getProperty("url");


    }
// Test method
    @Test
    public void gettestwithoutheaders() throws IOException, Exception
    {
        restclient = new RestClient();
        CloseableHttpResponse closablehttpresponse = restclient.Get(URL);
        int getstatuscode = closablehttpresponse.getStatusLine().getStatusCode();// get status code
        System.out.println(getstatuscode);
        Assert.assertEquals(getstatuscode, RESPONSE_STATUS_CODE_200, "Status is not 200");

        String responsestring = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8"); //utf-8 corrects the special
        //characters and returns pure string

        System.out.println(responsestring);
        Header[] hedderarray = closablehttpresponse.getAllHeaders();


        JSONObject respoonsejasonobject= new JSONObject(responsestring);
        System.out.println(respoonsejasonobject);

        // single value assertion
        String perpagevalue= TestUtil.getValueByJPath(respoonsejasonobject, "/per_page");
        System.out.println(perpagevalue);
        Assert.assertEquals(perpagevalue, "3");


        // getting value fron the jason array

        String lastname=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/last_name");
        String id=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/id");
        String firstname=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/first_name");
        String avtar=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/avatar");

        System.out.println(lastname);
        System.out.println(id);
        System.out.println(firstname);
        System.out.println(avtar);

        HashMap allheaders=new HashMap<String,String>();
        for(Header header:hedderarray)
        {
            allheaders.put(header.getName(), header.getValue());
        }

        System.out.println(allheaders);



    }


    @Test
    public void gettestwithheaders() throws IOException, Exception
    {
        restclient = new RestClient();
        HashMap<String, String> headerMap= new HashMap<String, String>();
        headerMap.put("content-Type", "application/json;");
        //  headerMap.put("username", "adarsh@123.com");
        CloseableHttpResponse closablehttpresponse = restclient.Get(URL, headerMap);

        // get status code
        int getstatuscode = closablehttpresponse.getStatusLine().getStatusCode();
        System.out.println(getstatuscode);
        Assert.assertEquals(getstatuscode, RESPONSE_STATUS_CODE_200, "Status is not 200");

        String responsestring = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8"); //utf-8 corrects the special
        //characters and returns pure string

        System.out.println(responsestring);
        Header[] hedderarray = closablehttpresponse.getAllHeaders();


        JSONObject respoonsejasonobject= new JSONObject(responsestring);
        System.out.println(respoonsejasonobject);

        // single value assertion
        String perpagevalue=TestUtil.getValueByJPath(respoonsejasonobject, "/per_page");
        System.out.println(perpagevalue);
        Assert.assertEquals(perpagevalue, "3");


        // getting value fron the jason array

        String lastname=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/last_name");
        String id=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/id");
        String firstname=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/first_name");
        String avtar=TestUtil.getValueByJPath(respoonsejasonobject, "/data[0]/avatar");

        System.out.println(lastname);
        System.out.println(id);
        System.out.println(firstname);
        System.out.println(avtar);

        HashMap allheaders=new HashMap<String,String>();
        for(Header header:hedderarray)
        {
            allheaders.put(header.getName(), header.getValue());
        }

        System.out.println(allheaders);



    }
}
