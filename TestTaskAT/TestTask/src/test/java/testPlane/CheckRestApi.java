package testPlane;

import funtions.RestApiFunction;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CheckRestApi {

    Response response;
    RestApiFunction restApiFunction = new RestApiFunction();
    private final static Logger logger = Logger.getLogger(CheckRestApi.class);

    @BeforeClass
    public void setUp(){
        restApiFunction.restApiSetUp("https://swapi.dev/api/");
    }

    @Test(description = "GET")
    public void getListEntityTest() {
       restApiFunction.getListEntity(response, logger);
    }

    @Test(description = "GET")
    public void getListFilmsTest() {
        restApiFunction.getFilmInfoTest(response, logger);
    }
}
