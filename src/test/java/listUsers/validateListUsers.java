package listUsers;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class validateListUsers {

    private static String param;
    private static Response response;
    private static int[] ids = {1, 2, 3};
    private static String[] names = {"João da Silva", "Maria Joaquina", "Ana Júlia"};
    private static int[] idades = {30, 25, 20};

    @BeforeClass
    public void setup() {
        baseURI = "http://restapi.wcaquino.me/";
        response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .get("/users");
    }

    @Test @BeforeClass
    public void headerTest() {
        response
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeContrato() {
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("GETListUsersSchema.json"));
    }

    @Test
    public void validarIds() {
        param = "id";
        response.then()
                    .body("[0]."+param, equalTo(ids[0]))
                    .body("[1]."+param, equalTo(ids[1]))
                    .body("[2]."+param, equalTo(ids[2]));
    }
    @Test
    public void validarNomes() {
        param = "name";
        response.then()
            .body("[0]."+param, equalTo(names[0]))
            .body("[1]."+param, equalTo(names[1]))
            .body("[2]."+param, equalTo(names[2]));
    }
    @Test
    public void validarIdade() {
        param = "age";
        response.then()
            .body("[0]."+param, equalTo(idades[0]))
            .body("[1]."+param, equalTo(idades[1]))
            .body("[2]."+param, equalTo(idades[2]));
    }

    @Test
    public void validarEnderecos() {
        param = "endereco";
        response.then()
            .body("[0]."+param, equalTo(null))
            .body("[1]."+param+".rua", equalTo("Rua dos bobos"))
            .body("[1]."+param+".numero", equalTo(0))
            .body("[2]."+param, equalTo(null));
    }
    @Test
    public void validarFilhos() {
        param = "filhos";
        response.then()
            .body("[0]."+param, equalTo(null))
            .body("[1]."+param, equalTo(null))
            .body("[2]."+param+"[0].name", equalTo("Zezinho"))
            .body("[2]."+param+"[1].name", equalTo("Luizinho"));
    }

}
