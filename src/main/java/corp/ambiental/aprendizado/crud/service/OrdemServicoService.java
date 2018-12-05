package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.model.dto.OrdemServicoDTO;
import corp.ambiental.aprendizado.crud.model.OrdemServico;
import corp.ambiental.aprendizado.crud.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ConversionService conversionService;

    @Transactional
    public OrdemServicoDTO criar(OrdemServicoDTO ordemServicoDto) {

        OrdemServico novaOrdemServico = ordemServicoRepository.save(prepararOrdemServico(ordemServicoDto));
        return conversionService.convert(novaOrdemServico, OrdemServicoDTO.class);
    }

    private OrdemServico prepararOrdemServico(OrdemServicoDTO ordemServicoDto) {

        return conversionService.convert(ordemServicoDto, OrdemServico.class);
    }

    public OrdemServicoDTO buscarPorIdOS(Long idOS) {

        OrdemServico ordemServico = consultarPorId(idOS);
        return conversionService.convert(ordemServico, OrdemServicoDTO.class);
    }

    private OrdemServico consultarPorId(Long idOS) {

        return ordemServicoRepository
                .findById(idOS)
                .orElseThrow(() -> new EntityNotFoundException("OS não encontrada"));
    }


    public List<OrdemServicoDTO> listarTodos(Pageable pageable) {

        Page<OrdemServico> listaOS = ordemServicoRepository.findAll(pageable);

        return listaOS
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public void excluir(Long idOS) {

        consultarPorId(idOS);
        ordemServicoRepository.deleteById(idOS);
    }

    public OrdemServicoDTO atualizar(OrdemServicoDTO osDto) {

        Long idOS = osDto.getId();

        if (idOS.equals(0L)) {
            throw new EntityNotFoundException("OS não encontrada");
        }

        consultarPorId(idOS);

        OrdemServico osNova = ordemServicoRepository.save(prepararOrdemServico(osDto));

        return conversionService.convert(osNova, OrdemServicoDTO.class);
    }

    private OrdemServicoDTO toDto(OrdemServico ordemServico) {
        return conversionService.convert(ordemServico, OrdemServicoDTO.class);
    }

}
