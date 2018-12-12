package corp.ambiental.aprendizado.crud.config.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.minio")
public class MinioProperties {

    private String urlMinio;
    private String accessKey;
    private String secretKey;

    public String getUrlMinio() {
        return urlMinio;
    }

    public void setUrlMinio(String urlMinio) {
        this.urlMinio = urlMinio;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
