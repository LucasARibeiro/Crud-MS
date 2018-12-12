package corp.ambiental.aprendizado.crud.service;

import com.google.common.collect.Lists;
import corp.ambiental.aprendizado.crud.model.Ligacao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessadorArquivosService {

    @Autowired
    private LigacaoService ligacaoService;

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
}
