package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.ClienteDTO;
import corp.ambiental.aprendizado.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ConversionService conversionService;

    @Transactional
    public ClienteDTO buscarPorIdCliente(Long idCliente) {

        Cliente cliente = consultarPorId(idCliente);

        return conversionService.convert(cliente, ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO salvar(ClienteDTO clienteDto) {

        Cliente clienteNovo = clienteRepository.save(prepararCliente(clienteDto));
        return conversionService.convert(clienteNovo, ClienteDTO.class);
    }

    private Cliente prepararCliente(ClienteDTO clienteDto) {

        return conversionService.convert(clienteDto, Cliente.class);
    }

    @Transactional
    public ClienteDTO atualizar(ClienteDTO clienteDTO) {

        Long idCliente = clienteDTO.getId();

        if (idCliente.equals(0L)) {
            throw new EntityNotFoundException("Cliente não cadastrado");
        }

        consultarPorId(idCliente);

        Cliente clienteNovo = clienteRepository.save(prepararCliente(clienteDTO));

        return conversionService.convert(clienteNovo, ClienteDTO.class);
    }

    private Cliente consultarPorId(Long idCliente) {
        return clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado"));
    }

    @Transactional
    public void excluir(Long idCliente) {

        consultarPorId(idCliente);
        clienteRepository.deleteById(idCliente);
    }
}
