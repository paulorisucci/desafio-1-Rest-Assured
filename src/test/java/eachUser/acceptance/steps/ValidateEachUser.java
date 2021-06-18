package eachUser.acceptance.steps;

import static io.restassured.RestAssured.*;

import cucumber.api.java.pt.*;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ValidateEachUser {

    @Dado("o site {string}")
    public void setup(String site){
        baseURI = site;
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    @Entao("valida o primeiro usuario")
    public void validateUser1() {
        given()
            .get("/users/1")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .assertThat()
                        .body(matchesJsonSchemaInClasspath("SchemaUser1.json"))
                        .body("id", equalTo(1))
                        .body("name", equalTo("João da Silva"))
                        .body("age", equalTo(30))
                        .body("salary", equalTo(1234.5678F));
    }
    @E("valida o segundo usuario")
    public void validateUser2() {
        given()
           .get("/users/2")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .assertThat()
                        .body(matchesJsonSchemaInClasspath("SchemaUser2.json"))
                        .body("id", equalTo(2))
                        .body("name", equalTo("Maria Joaquina"))
                        .body("endereco.rua", equalTo("Rua dos bobos"))
                        .body("endereco.numero", equalTo(0))
                        .body("age", equalTo(25))
                        .body("salary", equalTo(2500));
    }
    @E("valida o terceiro usuario")
    public void validateUser3() {
        given()
            .get("/users/3")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .assertThat()
                        .body(matchesJsonSchemaInClasspath("SchemaUser3.json"))
                        .body("id", equalTo(3))
                        .body("name", equalTo("Ana Júlia"))
                        .body("age", equalTo(20))
                        .body("filhos[0].name", equalTo("Zezinho"))
                        .body("filhos[1].name", equalTo("Luizinho"));
    }
    @E("valida o quarto usuario")
    public void validateUser4() {
        given()
           .get("/users/4")
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(404)
                    .assertThat()
                        .body(matchesJsonSchemaInClasspath("SchemaUser4.json"))
                        .body(equalTo("{\"error\":\"Usuário inexistente\"}"));
    }
}