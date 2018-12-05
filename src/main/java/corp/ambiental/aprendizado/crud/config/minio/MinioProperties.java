package corp.ambiental.aprendizado.crud.config.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.minio")
public class MinioProperties {

    private String urlMinio;
    private String acessKey;
    private String secretKey;

    public String getUrlMinio() {
        return urlMinio;
    }

    public void setUrlMinio(String urlMinio) {
        this.urlMinio = urlMinio;
    }

    public String getAcessKey() {
        return acessKey;
    }

    public void setAcessKey(String acessKey) {
        this.acessKey = acessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
