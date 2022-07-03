package automation.api.testes;

import automation.api.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TesteLogin extends TesteBase{

    private static final String LOGIN_USUARIO_ENDPOINT = "/login";

    @BeforeClass
    public static void setupRegistro(){
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    @Test
    public void testeNaoDeveLogarQuandoSenhaNaoForInformado() {
        Usuario usuario = new Usuario();
        usuario.setEmail("sydney@fife");

        given().
                body(usuario).
        when().
                post(LOGIN_USUARIO_ENDPOINT).
        then().
                body("error", is("Missing password"));
    }
}
