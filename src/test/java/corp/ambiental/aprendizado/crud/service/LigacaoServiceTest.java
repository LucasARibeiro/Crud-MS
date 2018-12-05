package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.environment.ClienteDTOEnvironment;
import corp.ambiental.aprendizado.crud.environment.ClienteEnvironment;
import corp.ambiental.aprendizado.crud.environment.LigacaoDTOEnvironment;
import corp.ambiental.aprendizado.crud.environment.LigacaoEnvironment;
import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import corp.ambiental.aprendizado.crud.repository.ClienteRepository;
import corp.ambiental.aprendizado.crud.repository.LigacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(MockitoExtension.class)
public class LigacaoServiceTest {

    @Mock
    private LigacaoRepository ligacaoRepository;

    @InjectMocks
    private LigacaoService ligacaoService;

    @Mock
    private ConversionService conversionService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private Pageable page;

    private Ligacao ligacao;

    private LigacaoDTO ligacaoDto;

    private Cliente cliente;

    private List<Ligacao> listaLigacoes;

    private List<LigacaoDTO> listaLigacoesDto;

    private Ligacao ligacao2;

    private ClienteDTO clienteDto;

    @BeforeEach
    void beforeEach() {
        ligacao = LigacaoEnvironment.criar();
        ligacao2 = LigacaoEnvironment.criar();
        ligacaoDto = LigacaoDTOEnvironment.criarDTO(2L);

        cliente = ClienteEnvironment.criar();
        clienteDto = ClienteDTOEnvironment.criarDTO(1L);

        List<LigacaoDTO> ligacaoDtos = new ArrayList<>();
        ligacaoDtos.add(ligacaoDto);

        listaLigacoesDto = Collections.unmodifiableList(ligacaoDtos);

        listaLigacoes = new ArrayList<>();
        listaLigacoes.add(ligacao);
        listaLigacoes.add(ligacao2);

    }

    @Test
    @DisplayName("Deve conseguir buscar uma ligacao pelo id quando for existente")
    void testBuscarPorId() {

        when(ligacaoRepository.findById(1L))
                .thenReturn(Optional.of(ligacao));

        ligacaoService.buscarPorIdLigacao(1L);

        verify(ligacaoRepository, times(1)).findById(1L);
        verify(conversionService, times(1)).convert(ligacao, LigacaoDTO.class);

        assertAll(()-> assertEquals(Long.valueOf(1), ligacao.getId()),
                ()->assertEquals(Long.valueOf(1), ligacao.getCliente().getId()));
    }

    @Test
    @DisplayName("Deve conseguir cadastrar uma nova ligacao")
    void testSalvarLigacao() {

        when(ligacaoRepository.save(any(Ligacao.class)))
                .thenReturn(ligacao);

        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(cliente));

        when(conversionService.convert(ligacaoDto, Ligacao.class))
                .thenReturn(ligacao);

        ligacaoService.salvar(ligacaoDto);

        verify(ligacaoRepository, times(1)).save(any(Ligacao.class));
        verify(conversionService, times(1)).convert(ligacaoDto, Ligacao.class);
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve conseguir atualizar uma Ligacao já cadastrada")
    void testAtualizarLigacao() {

        when(ligacaoRepository.findById(anyLong()))
                .thenReturn(Optional.of(ligacao));

        when(ligacaoRepository.save(any(Ligacao.class)))
                .thenReturn(ligacao);

        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(cliente));

        when(conversionService.convert(ligacaoDto, Ligacao.class))
                .thenReturn(ligacao);

        ligacaoService.atualizar(ligacaoDto);

        verify(ligacaoRepository, times(1)).findById(2L);
        verify(ligacaoRepository, times(1)).save(ligacao);
        verify(clienteRepository, times(1)).findById(1L);
        verify(conversionService, times(1)).convert(ligacaoDto, Ligacao.class);

    }

    @Test
    @DisplayName("Deve conseguir excluir uma ligacao existente")
    void excluirLigacao() {

        when(ligacaoRepository.findById(anyLong()))
                .thenReturn(Optional.of(ligacao));

        doNothing()
                .when(ligacaoRepository)
                .deleteById(anyLong());

        ligacaoService.excluir(2L);

        verify(ligacaoRepository, times(1))
                .findById(2L);

        verify(ligacaoRepository, times(1))
                .deleteById(2L);
    }

    @Test
    @DisplayName("Deve conseguir fazer a busca pela lista de ligações paginadas")
    void listarPaginado() {

        page = PageRequest.of(1, 2);

        Page<Ligacao> paginaLigacao = new PageImpl<>(listaLigacoes);

        when(ligacaoRepository.findAll(page))
                .thenReturn(paginaLigacao);

        ligacaoService.listarTodos(page);

        verify(ligacaoRepository, times(1))
                .findAll(page);

        List<Object> bla = new ArrayList<>();

        paginaLigacao.stream().forEach((ligacao) -> {
            assertAll(() -> assertEquals(Long.valueOf(1), ligacao.getId()));
        });
        assertAll(() -> assertEquals(2, page.getPageSize()),
                () -> assertEquals(1, page.getPageNumber()));
    }


    @Test
    @DisplayName("Deve fazer a busca das ligações vinculadas a um cliente especifico")
    void listarPorIdCliente() {

        doReturn(listaLigacoes).when(ligacaoRepository).findByIdCliente(anyLong());

        when(conversionService.convert(ligacao, LigacaoDTO.class))
                .thenReturn(ligacaoDto);

        ligacaoService.listarPorIdCliente(1L);

        verify(ligacaoRepository, times(1))
                .findByIdCliente(1L);

        assertAll(() -> assertEquals(Long.valueOf(1), listaLigacoes.get(0).getId()),
                () -> assertEquals(Long.valueOf(1), listaLigacoes.get(0).getCliente().getId()));


    }

}
