package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.model.Cliente;
import corp.ambiental.aprendizado.crud.model.dto.LigacaoDTO;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import corp.ambiental.aprendizado.crud.repository.ClienteRepository;
import corp.ambiental.aprendizado.crud.repository.LigacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LigacaoService {

    @Autowired
    private LigacaoRepository ligacaoRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ClienteRepository clienteRepository;

    public LigacaoDTO buscarPorIdLigacao(Long idLigacao) {

        Ligacao ligacao = consultarPorId(idLigacao);

        return conversionService.convert(ligacao, LigacaoDTO.class);
    }

    private Ligacao consultarPorId(Long idLigacao) {

        return ligacaoRepository
                .findById(idLigacao)
                .orElseThrow(() -> new EntityNotFoundException("Ligação não encontrada"));

    }

    @Transactional
    public LigacaoDTO salvar(LigacaoDTO ligacaoDTO) {

        Ligacao ligacaoNova = ligacaoRepository.save(prepararLigacao(ligacaoDTO));
        return conversionService.convert(ligacaoNova, LigacaoDTO.class);
    }

    private Ligacao prepararLigacao(LigacaoDTO ligacaoDto) {

        Long idCliente = ligacaoDto.getCliente();
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new EntityNotFoundException("Cliente não vinculado a Ligação"));

        Ligacao ligacao = conversionService.convert(ligacaoDto, Ligacao.class);
        ligacao.setCliente(cliente);

        return ligacao;
    }

    @Transactional
    public void excluir(Long idLigacao) {

        consultarPorId(idLigacao);

        ligacaoRepository.deleteById(idLigacao);
    }

    @Transactional
    public LigacaoDTO atualizar(LigacaoDTO ligacaoDto) {

        Long idLigacaoAntiga = ligacaoDto.getId();

        if (idLigacaoAntiga.equals(0L)) {
            throw new EntityNotFoundException("Ligação não existente");
        }

        consultarPorId(idLigacaoAntiga);

        Ligacao ligacaoNova = ligacaoRepository.save(prepararLigacao(ligacaoDto));

        return conversionService.convert(ligacaoNova, LigacaoDTO.class);

    }

    public List<LigacaoDTO> listarPorIdCliente(Long idCliente) {
        List<Ligacao> ligacoes;

        ligacoes = ligacaoRepository.findByIdCliente(idCliente);

        return ligacoes
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public List<LigacaoDTO> listarTodos(Pageable pageRequest) {

        Page<Ligacao> listaLigacao = ligacaoRepository.findAll(pageRequest);

        return listaLigacao
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LigacaoDTO toDto(Ligacao ligacao) {
        return conversionService.convert(ligacao, LigacaoDTO.class);
    }
}