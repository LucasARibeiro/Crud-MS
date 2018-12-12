package corp.ambiental.aprendizado.crud.resource;

import corp.ambiental.aprendizado.crud.environment.OrdemServicoDTOEnvironment;
import corp.ambiental.aprendizado.crud.environment.OrdemServicoEnvironment;
import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.model.OrdemServico;
import corp.ambiental.aprendizado.crud.service.OrdemServicoService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrdemServicoResourceTest {

    @Mock
    private OrdemServicoService ordemServicoService;

    @InjectMocks
    private OrdemServicoResource ordemServicoResource;

    private OrdemServico ordemServico;

    private OrdemServicoDTO ordemServicoDto;
    private OrdemServicoDTO ordemServicoDtoIdZero;

    private OrdemServicoDTO ordemServiceDto1;
    private OrdemServicoDTO ordemServiceDto2;
    private OrdemServicoDTO ordemServiceDto3;

    private final String URL = "/api/cadastro/ordemSevico";

    @BeforeEach
    void beforeEach() {

        ordemServico = OrdemServicoEnvironment.criar();
        ordemServicoDto = OrdemServicoDTOEnvironment.criar(1L);
        ordemServicoDtoIdZero = OrdemServicoDTOEnvironment.criar(0L);

        ordemServiceDto1 = OrdemServicoDTOEnvironment.criar(1L);
        ordemServiceDto2 = OrdemServicoDTOEnvironment.criar(2L);
        ordemServiceDto3 = OrdemServicoDTOEnvironment.criar(3L);
    }

    @Test
    void testCriarOS() {
        doReturn(ordemServicoDto).when(ordemServicoService).criar(any(OrdemServicoDTO.class));

        given()
                .standaloneSetup(ordemServicoResource)
                .contentType(ContentType.JSON)
                .body(ordemServicoDtoIdZero)
                .post(URL + "/novaOrdem")
                .then()
                .statusCode(201);

        verify(ordemServicoService).criar(any(OrdemServicoDTO.class));
    }

    @Test
    void testConsultarPeloId() {

        doReturn(ordemServicoDto).when(ordemServicoService).buscarPorIdOS(anyLong());

        given()
                .standaloneSetup(ordemServicoResource)
                .get(URL + "/consultar/1")
                .then()
                .statusCode(200);
        verify(ordemServicoService).buscarPorIdOS(anyLong());
    }

    @Test
    @DisplayName("Deve listar todas as Ordem de Serviços")
    void testListarTodos() {
        List<OrdemServicoDTO> listaOrdemServicoDto = new ArrayList<>();

        listaOrdemServicoDto.add(ordemServiceDto1);
        listaOrdemServicoDto.add(ordemServiceDto2);
        listaOrdemServicoDto.add(ordemServiceDto3);

        Pageable pageable = PageRequest.of(0, 2);
        doReturn(listaOrdemServicoDto).when(ordemServicoService).listarTodos(pageable);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(ordemServicoResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        given()
                .standaloneSetup(ordemServicoResource)
                .queryParam("page", 0)
                .queryParam("size", 2)
                .get(URL + "/listar")
                .then()
                .statusCode(200);

        verify(ordemServicoService).listarTodos(any());
    }

    @Test
    @DisplayName("Deve atualizar uma Ordem de Servico")
    void testAtualizarOdemServico() {
        doReturn(ordemServicoDto).when(ordemServicoService).atualizar(any(OrdemServicoDTO.class));

        given()
                .standaloneSetup(ordemServicoResource)
                .body(ordemServicoDto)
                .contentType(ContentType.JSON)
                .put(URL + "/atualizar")
                .then()
                .statusCode(200);
        verify(ordemServicoService).atualizar(any(OrdemServicoDTO.class));
    }

    @Test
    @DisplayName("Deve excluir uma Ordem de Servico")
    void testExcluirOrdemServico() {
        doNothing().when(ordemServicoService).excluir(anyLong());

        given()
                .standaloneSetup(ordemServicoResource)
                .delete(URL + "/excluir/1")
                .then()
                .statusCode(204);
        verify(ordemServicoService).excluir(anyLong());
    }

}
