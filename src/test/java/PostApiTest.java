import com.api.TestBase.TestBase;
import com.api.client.RestClient;
import com.api.data.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

public class PostApiTest extends TestBase {

    TestBase testbase;
    String urlpost;
    RestClient restclient;
    CloseableHttpResponse closablehttpresponse;

    @BeforeMethod
    public void SetUp() throws Exception
    {
        testbase= new TestBase();
        urlpost=prop.getProperty("urlpost");
    }

    @Test
    public void postAPITest() throws Exception
    {
        restclient= new RestClient();
        HashMap<String, String> headerMap= new HashMap<String, String>();
        headerMap.put("content-Type", "application/json;");

        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("morpheus","leader"); //expected user object

        //object to jason conversion

        mapper.writeValue(new File("D:\\workspace\\api\\automationAPI\\src\\main\\java\\com\\api\\data\\users.json"),users);

        //object to json string

        String usersJSONstrin=mapper.writeValueAsString(users);
        System.out.println(usersJSONstrin);
        closablehttpresponse = restclient.post(urlpost,usersJSONstrin,headerMap);
        //System.out.println(closablehttpresponse);

        //1. Status code
        int statuscode= closablehttpresponse.getStatusLine().getStatusCode();
        System.out.println(statuscode);

        //2. javastring

        String responsestring= EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
        JSONObject responseJson= new JSONObject(responsestring);
        System.out.println(responseJson);

        //json to java object
        Users userobj= mapper.readValue(responsestring, Users.class); //actual user object
        System.out.println(userobj);

        //System.out.println(users.getName().equals(userobj.getName()));



    }

}
