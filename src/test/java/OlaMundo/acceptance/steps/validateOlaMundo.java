package olaMundo.acceptance.steps;



import cucumber.api.java.pt.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;


public class ValidateOlaMundo {

    private Response response;



    @Dado("o site {string}")
    public void defineURI(String site) {
        baseURI = site;
    }

    @Quando("verifico o conteúdo da página {string}")
    public void verificarConteudo(String page) {
        response = get(page);
    }

    @Entao("o valor recebido é {string}")
    public void validarMensagem(String mensagem) {
        response.then()
                .statusCode(200)
                .body(equalTo(mensagem));
    }
}
