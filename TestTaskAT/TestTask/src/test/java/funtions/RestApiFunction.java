package funtions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import utils.DataTest;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestApiFunction {
    DataTest dataTest = new DataTest();

    public void restApiSetUp(String uri){
        RestAssured.baseURI = uri;
    }

    public void getListEntity(Response response, Logger logger) {
        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info(response.prettyPrint());
    }

    private String getListFilmsTest(Response response, Logger logger) {

        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get("films/");

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath path = response.jsonPath();
        String nameFilm = "";
        String url = "";
        List<HashMap<String, Object>> results = path.getList("results");
        try{
            for (HashMap<String, Object> film : results) {
                String title= (String) film.get("title");
                logger.info(title);

                if(title.equals(dataTest.FILM)){
                    nameFilm=title;
                    url = (String) film.get("url");
                }
            }
            logger.info("Был выбран фильм \""+nameFilm+"\"");
        }catch (Exception ex){
            logger.debug(ex);
        }

        return url;
    }

    public void getFilmInfoTest(Response response, Logger logger) {

        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(getListFilmsTest(response, logger));

        Assert.assertEquals(response.statusCode(), 200);

        logger.info(response.prettyPrint());
    }
}
