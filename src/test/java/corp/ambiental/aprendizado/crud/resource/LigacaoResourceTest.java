
package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.environment.LigacaoDTOEnvironment;
import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.service.LigacaoService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LigacaoResourceTest {
/*    @Mock
    private LigacaoService ligacaoService;

    @InjectMocks
    private LigacaoResource ligacaoResource;

    private final String URL = "/api/cadastro/ligacao";

    private LigacaoDTO ligacaoDTO;
    private LigacaoDTO ligacaoDtoIdZero;
    private List<LigacaoDTO> listaLigacoesDto;
    private LigacaoDTO ligacaoDto;
    private LigacaoDTO ligacaoDto2;

    @BeforeEach
    void before() {
        ligacaoDTO = LigacaoDTOEnvironment.criarDTO(1L);
        ligacaoDtoIdZero = LigacaoDTOEnvironment.criarDTO(0L);
        ligacaoDto = LigacaoDTOEnvironment.criarDTO(2L);
        ligacaoDto2 = LigacaoDTOEnvironment.criarDTO(3L);
    }

    @Test
    @DisplayName("Deve conseguir buscar uma ligação pelo id")
    void testBuscaPorIdLigacao() {
        doReturn(ligacaoDTO).when(ligacaoService).buscarPorIdLigacao(anyLong());

        given()
                .standaloneSetup(ligacaoResource)
                .get(URL + "/consultarPeloIdLigacao/1")
                .then()
                .statusCode(200);
        verify(ligacaoService).buscarPorIdLigacao(any());
    }

    @Test
    @DisplayName("Deve conseguir inserir uma nova ligação")
    void testDeveInserirUmaNovaLigacao() {

        doReturn(ligacaoDTO).when(ligacaoService).salvar(any(LigacaoDTO.class));

        given()
                .standaloneSetup(ligacaoResource)
                .contentType(ContentType.JSON)
                .body(ligacaoDtoIdZero)
                .post(URL + "/nova")
                .then()
                .statusCode(201);
        verify(ligacaoService).salvar(any(LigacaoDTO.class));
    }

    @Test
    @DisplayName("Deve conseguir atualizar uma ligação ja existente")
    void testDeveAtualizarUmaLigacao() {

        doReturn(ligacaoDTO).when(ligacaoService).atualizar(any(LigacaoDTO.class));

        given()
                .standaloneSetup(ligacaoResource)
                .body(ligacaoDTO)
                .contentType(ContentType.JSON)
                .put(URL + "/nova")
                .then()
                .statusCode(200);

        verify(ligacaoService).atualizar(any(LigacaoDTO.class));
    }

    @Test
    @DisplayName("Deve conseguir excluir uma Ligação pelo id")
    void testDeveExcluirUmaLigacao() {
        doNothing().when(ligacaoService).excluir(anyLong());

        given()
                .standaloneSetup(ligacaoResource)
                .delete(URL + "/excluir/1")
                .then()
                .statusCode(204);
        verify(ligacaoService).excluir(anyLong());
    }

    @Test
    @DisplayName("Deve conseguir retornar uma lista de ligações paginado")
    void testBuscarTodosPaginado() {
        List<LigacaoDTO> listaLigacoesDto = new ArrayList<>();
        listaLigacoesDto.add(ligacaoDto);
        listaLigacoesDto.add(ligacaoDTO);
        listaLigacoesDto.add(ligacaoDto2);

        Pageable pageable = PageRequest.of(0,1);
        doReturn(listaLigacoesDto).when(ligacaoService).listarTodos(pageable);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(ligacaoResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        given()
                .standaloneSetup(ligacaoResource)
                .queryParam("page", 0)
                .queryParam("size", 1)
                .get(URL + "/listar")
                .then()
                .statusCode(200);

        verify(ligacaoService).listarTodos(any());
    }
*/
}