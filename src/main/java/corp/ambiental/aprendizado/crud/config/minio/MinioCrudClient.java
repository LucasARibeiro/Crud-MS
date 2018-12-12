package corp.ambiental.aprendizado.crud.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(value = "singleton")
@Component
@EnableConfigurationProperties(MinioProperties.class)
public class MinioCrudClient {

    @Autowired
    private MinioProperties minioProperties;

    private MinioClient minioClient;

    public MinioClient getMinioClient() {
        if (Objects.isNull(minioClient)) {
            try {
                this.minioClient = new MinioClient(minioProperties.getUrlMinio(),
                        minioProperties.getAccessKey(), minioProperties.getSecretKey());

            } catch (Exception e) {
                throw new Error("Erro ao buscar as configurações do minio");
            }
        }
        return minioClient;
    }
}
