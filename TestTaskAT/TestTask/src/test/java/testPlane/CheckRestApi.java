package testPlane;

import funtions.RestApiFunction;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CheckRestApi {

    RestApiFunction restApiFunction = new RestApiFunction();

    @Test(description = "GET")
    public void getListEntityTest() {
        restApiFunction.checkRestApi();
    }
}
