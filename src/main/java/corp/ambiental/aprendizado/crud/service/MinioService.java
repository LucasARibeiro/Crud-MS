package corp.ambiental.aprendizado.crud.service;

import corp.ambiental.aprendizado.crud.config.minio.MinioCrudClient;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MinioService {

    @Autowired
    private MinioCrudClient minioCrudClient;

    public String uploadArquivo(final File arquivo, final String contentType) {
        String nomeBucket = "Arquivo-Crud";

        try {
            MinioClient minioClient = getMinioClientECriaBucket(nomeBucket);
            minioClient.putObject(nomeBucket, arquivo.getName(), arquivo.getPath(), contentType);
            return minioClient.presignedGetObject(nomeBucket, arquivo.getName());
        } catch (Exception e) {
            throw new Error("Erro Desconhecido");
        }
    }

    private MinioClient getMinioClientECriaBucket(String nomeBucket) {
        MinioClient minioClient = minioCrudClient.getMinioClient();
        try {
            if (!minioClient.bucketExists(nomeBucket)) {
                minioClient.makeBucket(nomeBucket);
            }
        } catch (Exception e) {
            throw new Error(e);
        }
        return minioClient;
    }

    public static void main(String[] args) {

        File file = new File("teste");

        MinioService minioService = new MinioService();

        minioService.uploadArquivo(file,"seila");
    }

}

