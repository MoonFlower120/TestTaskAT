package testPlane;

import static io.restassured.RestAssured.get;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;


public class CheckRestApi {

    Response response;
    private final static Logger logger = Logger.getLogger(CheckRestApi.class);

    @Test(description = "GET")
    public void getRequestExampleTest() throws JSONException {
        Response response = get("http://restcountries.eu/rest/v1/name/russia");
        JSONArray jsonResponse = new JSONArray(response.asString());
        String capital = jsonResponse.getJSONObject(0).getString("capital");
        Assert.assertEquals(capital, "Moscow");
    }
}
