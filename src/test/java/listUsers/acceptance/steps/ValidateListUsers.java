package listUsers.acceptance.steps;

import static io.restassured.RestAssured.*;

import cucumber.api.java.es.Dado;
import cucumber.api.java.pt.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ValidateListUsers {

    private static String param;
    private static Response response;
    private static int[] ids = {1, 2, 3};
    private static String[] names = {"João da Silva", "Maria Joaquina", "Ana Júlia"};
    private static int[] idades = {30, 25, 20};

    @Dado("o site {string}")
    public void setup(String site) {
        baseURI = site;
    }

    @Quando("fizer uma requisição GET")
    public void requisicaoGET(){
        response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .get("/users");
    }

    @Então("verifique a resposta")
    public void headerTest() {
        response
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @E("faça o teste de contrato")
    public void testeContrato() {
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("GETListUsersSchema.json"));
    }

    @E("valide os Ids")
    public void validarIds() {
        param = "id";
        response.then()
                    .body("[0]."+param, equalTo(ids[0]))
                    .body("[1]."+param, equalTo(ids[1]))
                    .body("[2]."+param, equalTo(ids[2]));
    }

    @E("valide os nomes")
    public void validarNomes() {
        param = "name";
        response.then()
            .body("[0]."+param, equalTo(names[0]))
            .body("[1]."+param, equalTo(names[1]))
            .body("[2]."+param, equalTo(names[2]));
    }

    @E("valide as idades")
    public void validarIdade() {
        param = "age";
        response.then()
            .body("[0]."+param, equalTo(idades[0]))
            .body("[1]."+param, equalTo(idades[1]))
            .body("[2]."+param, equalTo(idades[2]));
    }

    @E("valide os endereços")
    public void validarEnderecos() {
        param = "endereco";
        response.then()
            .body("[0]."+param, equalTo(null))
            .body("[1]."+param+".rua", equalTo("Rua dos bobos"))
            .body("[1]."+param+".numero", equalTo(0))
            .body("[2]."+param, equalTo(null));
    }

    @E("valide os dependentes")
    public void validarFilhos() {
        param = "filhos";
        response.then()
            .body("[0]."+param, equalTo(null))
            .body("[1]."+param, equalTo(null))
            .body("[2]."+param+"[0].name", equalTo("Zezinho"))
            .body("[2]."+param+"[1].name", equalTo("Luizinho"));
    }
}
