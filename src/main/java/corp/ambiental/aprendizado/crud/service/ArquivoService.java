package corp.ambiental.aprendizado.crud.service;

import com.google.common.collect.Lists;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import corp.ambiental.aprendizado.crud.model.dto.ArquivoDTO;
import corp.ambiental.aprendizado.crud.repository.ArquivoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ArquivoService {

    @Autowired
    private LigacaoService ligacaoService;
    @Autowired
    private ArquivoRepository arquivoRepository;
    @Autowired
    private MinioService minioService;


    public void criarArquivo(FileWriter fileWriter) throws IOException {
        try (FileWriter novoFile = fileWriter;
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID,CLIENTE,CIDADE,LOGRADOURO,CEP"))) {
            Iterable it = ligacaoService.listarTodos();
            List<Ligacao> listaLigacao = Lists.newArrayList(it);

            for (Ligacao ligacao : listaLigacao) {
                List<Object> data = Arrays.asList(
                        ligacao.getId(),
                        ligacao.getCliente().getNome(),
                        ligacao.getCidade(),
                        ligacao.getLogradouro(),
                        ligacao.getCep());

                csvPrinter.printRecord(data);
            }
            fileWriter.flush();
        }
    }

    public ArquivoDTO salvarArquivoMinio(ArquivoDTO arquivoDTO) throws IOException {
        criarNovoArquivo(arquivoDTO);
        return arquivoDTO;
    }

    private File criarNovoArquivo(ArquivoDTO arquivoDTO) throws IOException {
        File file = new File(arquivoDTO.getNome() + "." + arquivoDTO.getFormato());
        FileWriter fileWriter = new FileWriter(file);
        List<Tuple> resultado = arquivoRepository.realizaConsulta(arquivoDTO);
        preencherArquivo(fileWriter, resultado);
        minioService.uploadArquivo(file, arquivoDTO.getNomeBucket());
        return file;
    }

    private void preencherArquivo(FileWriter fileWriter, List<Tuple> resultado) throws IOException {
        String[] tuple = resultado.get(0).getElements()
                .stream()
                .map(TupleElement::getAlias)
                .toArray(String[]::new);
        final CSVPrinter csvPrinter = new CSVPrinter(fileWriter,
                CSVFormat.EXCEL
                        .withDelimiter(',')
                        .withHeader(tuple));
        inserirDados(fileWriter, resultado, csvPrinter);
    }

    private void inserirDados(FileWriter fileWriter, List<Tuple> resultado, CSVPrinter csvPrinter) throws IOException {
        resultado.forEach(tuple -> {
            tuple.getElements()
                    .forEach(ele -> {
                        try {
                            csvPrinter.print(tuple.get(ele.getAlias(), ele.getJavaType()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            try {
                csvPrinter.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.flush();
    }
}
