package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.environment.ClienteDTOEnvironment;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.service.ClienteService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteResourceTest {
/*
    @Mock
    private ClienteService mockClienteService;

    @InjectMocks
    private ClienteResource mockClienteResource;

    private final String URL = "/api/cadastro/cliente";

    private ClienteDTO clienteDTO;
    private ClienteDTO clienteDtoIdZero;

    @BeforeEach
    void beforeEach() {
        clienteDTO = ClienteDTOEnvironment.criarDTO(1L);
        clienteDtoIdZero = ClienteDTOEnvironment.criarDTO(0L);
    }

    @Test
    @DisplayName("Deve conseguir retornar um cliente pelo id")
    void testConsultaCliente() {
        doReturn(clienteDTO).when(mockClienteService).buscarPorIdCliente(anyLong());

        given()
                .standaloneSetup(mockClienteResource)
                .get(URL + "/listar-por-id/1")
                .then()
                .statusCode(200);
        verify(mockClienteService).buscarPorIdCliente(anyLong());
    }

    @Test
    @DisplayName("Deve conseguir inserir um novo cliente")
    void testCriarNovoCliente() {
        doReturn(clienteDTO).when(mockClienteService).salvar(any(ClienteDTO.class));

        given()
                .standaloneSetup(mockClienteResource)
                .contentType(ContentType.JSON)
                .body(clienteDtoIdZero)
                .post(URL + "/novo")
                .then()
                .statusCode(201);

        verify(mockClienteService).salvar(any(ClienteDTO.class));

    }

    @Test
    @DisplayName("Deve conseguir atualizar um cliente ja existente")
    void testAtualizarCliente() {
        doReturn(clienteDTO).when(mockClienteService).atualizar(any(ClienteDTO.class));

        given()
                .standaloneSetup(mockClienteResource)
                .contentType(ContentType.JSON)
                .body(clienteDTO)
                .put(URL + "/novo")
                .then()
                .statusCode(200);

        verify(mockClienteService).atualizar(any(ClienteDTO.class));
    }

    @Test
    @DisplayName("Deve conseguir excluir um cliente")
    void excluirCliente() {
        doNothing().when(mockClienteService).excluir(anyLong());

        given()
                .standaloneSetup(mockClienteResource)
                .delete(URL + "/delete/1")
                .then()
                .statusCode(204);

        verify(mockClienteService).excluir(anyLong());
    }
*/
}
