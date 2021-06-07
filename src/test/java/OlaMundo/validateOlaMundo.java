package OlaMundo;


import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;


public class validateOlaMundo {

    @BeforeClass
    public void defineURI() {
        baseURI = "http://restapi.wcaquino.me/";
    }

    @Test
    public void GETOlaMundo() {
        get("/ola").
            then().
                body(equalTo("Ola Mundo!"));
    }
}
