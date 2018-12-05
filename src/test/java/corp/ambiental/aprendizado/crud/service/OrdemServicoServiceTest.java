package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.environment.OrdemServicoEnvironment;
import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.model.OrdemServico;
import corp.ambiental.aprendizado.crud.repository.OrdemServicoRepository;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class OrdemServicoServiceTest {

    @Mock
    private OrdemServicoRepository ordemServicoRepository;

    @InjectMocks
    private OrdemServicoService ordemServicoService;

    @Mock
    private ConversionService conversionService;

    @Mock
    private Pageable page;

    private OrdemServico ordemServico;
    private OrdemServico ordemServico2;
    private OrdemServico ordemServico3;

    private OrdemServicoDTO ordemServicoDto;

    private List<OrdemServico> listaOS;

    @BeforeEach
    void beforeEach() {
        ordemServico = OrdemServicoEnvironment.criar();
        ordemServico2 = OrdemServicoEnvironment.criar();
        ordemServico3 = OrdemServicoEnvironment.criar();
    }

    @Test
    void testSalvarOS() {
        when(ordemServicoRepository.save(any(OrdemServico.class)))
                .thenReturn(ordemServico);

        when(conversionService.convert(ordemServicoDto, OrdemServico.class))
                .thenReturn(ordemServico);

        ordemServicoService.criar(ordemServicoDto);

        verify(ordemServicoRepository, times(1)).save(any(OrdemServico.class));
        verify(conversionService, times(1)).convert(ordemServicoDto, OrdemServico.class);
    }

    @Test
    void testBuscarPorIdOS() {
        when(ordemServicoRepository.findById(anyLong()))
                .thenReturn(Optional.of(ordemServico));

        ordemServicoService.buscarPorIdOS(1L);

        verify(ordemServicoRepository, times(1)).findById(1L);
        assertAll(() -> assertEquals(Long.valueOf(1), ordemServico.getId()));
    }

    @Test
    void testListarTodosPaginados() {

        listaOS = new ArrayList<>();
        listaOS.add(ordemServico);
        listaOS.add(ordemServico2);
        listaOS.add(ordemServico3);

        page = PageRequest.of(1, 2);

        Page<OrdemServico> paginaOS = new PageImpl<>(listaOS);

        when(ordemServicoRepository.findAll(page))
                .thenReturn(paginaOS);

        ordemServicoService.listarTodos(page);

        verify(ordemServicoRepository, times(1))
                .findAll(page);

        paginaOS.stream().forEach((ordemServico) -> {
            assertAll(() -> assertEquals(Long.valueOf(1), ordemServico.getId()));
        });
        assertAll(() -> assertEquals(2, page.getPageSize()),
                () -> assertEquals(1, page.getPageNumber()));
    }

    @Test
    void testExcluirOS() {

        when(ordemServicoRepository.findById(anyLong()))
                .thenReturn(Optional.of(ordemServico));
        doNothing()
                .when(ordemServicoRepository)
                .deleteById(anyLong());
        ordemServicoService.excluir(3L);

        verify(ordemServicoRepository, times(1))
                .findById(3L);
        verify(ordemServicoRepository, times(1))
                .deleteById(3L);
    }
/*
    @Test
    void testDeveAtualizarUmaOSExistente() {

        when(ordemServicoRepository.findById(anyLong()))
                .thenReturn(Optional.of(ordemServico));
        when(ordemServicoRepository.save(any(OrdemServico.class)))
                .thenReturn(ordemServico);
        when(conversionService.convert(ordemServicoDto, OrdemServico.class))
                .thenReturn(ordemServico);

        ordemServicoService.atualizar(ordemServicoDto);

        verify(ordemServicoRepository, times(1)).findById(3L);
        verify(ordemServicoRepository, times(1)).save(ordemServico);
        verify(conversionService, times(1)).convert(ordemServicoDto, OrdemServico.class);
    }

*/
}

