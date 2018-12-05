package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.environment.ClienteDTOEnvironment;
import corp.ambiental.aprendizado.crud.environment.ClienteEnvironment;
import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService mockClienteService;

    @Mock
    private ClienteRepository mockClienteRepository;

    @Mock
    private ConversionService conversionService;

    private Cliente cliente;

    private ClienteDTO clienteDto;

    @BeforeEach
    void beforeEach() {

        cliente = ClienteEnvironment.criar();
        clienteDto = ClienteDTOEnvironment.criarDTO(1L);
    }

    @Test
    @DisplayName("Deve buscar um cliente pelo id")
    void consultarPorId() {

        when(mockClienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(cliente));
        when(conversionService.convert(cliente, ClienteDTO.class))
                .thenReturn(clienteDto);

        mockClienteService.buscarPorIdCliente(1L);

        verify(mockClienteRepository, times(1))
                .findById(1L);
        verify(conversionService, times(1))
                .convert(cliente, ClienteDTO.class);
    }

    @Test
    @DisplayName("Deve conseguir inserir um novo cliente")
    void salvarCliente() {

        when(mockClienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);
        when(conversionService.convert(clienteDto, Cliente.class))
                .thenReturn(cliente);

        mockClienteService.salvar(clienteDto);

        verify(mockClienteRepository, times(1))
                .save(cliente);
        verify(conversionService, times(1))
                .convert(clienteDto, Cliente.class);
    }


    @Test
    @DisplayName("Deve conseguir atualizar um cliente ja existente")
    void atualizarCliente() {

        when(mockClienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(cliente));
        when(mockClienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);
        when(conversionService.convert(clienteDto, Cliente.class))
                .thenReturn(cliente);

        mockClienteService.atualizar(clienteDto);

        verify(mockClienteRepository, times(1))
                .findById(1L);
        verify(mockClienteRepository, times(1))
                .save(cliente);
        verify(conversionService, times(1))
                .convert(clienteDto, Cliente.class);

    }

    @Test
    @DisplayName("Deve conseguir deletar um cliente existente")
    void deletarCliente() {

        when(mockClienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(cliente));

        doNothing()
                .when(mockClienteRepository)
                .deleteById(anyLong());

        mockClienteService.excluir(1L);

        verify(mockClienteRepository, times(1))
                .findById(1L);
        verify(mockClienteRepository, times(1))
                .deleteById(1L);


    }


}
