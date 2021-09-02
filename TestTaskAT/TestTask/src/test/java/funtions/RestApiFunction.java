package funtions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import testPlane.CheckRestApi;
import utils.DataTest;

import java.util.*;

import static io.restassured.RestAssured.given;

public class RestApiFunction {

    Response response;
    private final static Logger logger = Logger.getLogger(CheckRestApi.class);
    DataTest dataTest = new DataTest();

    /////////////////////////////////////////////////////
    public void checkRestApi(){
        try{
            String filmsListUrl = getRootEntities("https://swapi.dev/api/");
            String filmUrl = getListFilms(filmsListUrl).get(0);
            getInfo(filmUrl);
            String planetUrl = getListEntities(filmUrl, "planets").get(0);
            getInfo(planetUrl);
            String speciesUrl = getListEntities(filmUrl, "species").get(0);
            getInfo(speciesUrl);
            List<String> starshipsList = getListEntities(filmUrl, "starships");
            String starshipsUrl = starshipsList.get(starshipsList.size()-1);
            getInfo(starshipsUrl);
            getListEntities(starshipsUrl, "pilots");
        }catch (Exception ex){
            logger.debug(ex);
        }
    }
    ////////////////////////////////////////////////////

    private void getInfo(String basePath) {

        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(basePath);

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath path = response.jsonPath();
        Map<String, String> info = path.getMap("$");
        logger.info("Информация: " + info);
    }

    private String getRootEntities(String basePath){
        //получить список сущностей
        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(basePath);
        Assert.assertEquals(response.statusCode(), 200);
        //получить ссылку
        JsonPath path = response.jsonPath();
        Map<String, String> listEntities = path.getMap("$");
        Set<String> listKey = listEntities.keySet();
        String url = path.get("films");
        logger.info("Список сущностей: " + listKey);
        logger.info("Ссылка на список фильмов: " + url);
        return url;
    }

    private List<String> getListFilms(String basePath) {
        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(basePath);

        Assert.assertEquals(response.statusCode(), 200);
        JsonPath path = response.jsonPath();
        List<String> url = new ArrayList<>();
        List<HashMap<String, Object>> results = path.getList("results");
        try{
            for (HashMap<String, Object> film : results) {
                url.add((String) film.get("url"));
            }
            logger.info("Список фильмов: " + url);
        }catch (Exception ex){
            logger.debug(ex);
        }

        return url;
    }

    private List<String> getListEntities(String basePath, String node){
        //получить список сущностей
        response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(basePath);
        Assert.assertEquals(response.statusCode(), 200);
        //получить ссылку
        JsonPath path = response.jsonPath();
        List<String> listPlanets = path.getList(node);
        logger.info("Список " + node + ": " + listPlanets);
        return listPlanets;
    }
}
