package testPlane;

import webForms.RestApiFunction;
import org.testng.annotations.Test;


public class CheckRestApi {

    RestApiFunction restApiFunction = new RestApiFunction();

    @Test(description = "GET")
    public void getListEntityTest() {
        restApiFunction.checkRestApi();
    }
}
