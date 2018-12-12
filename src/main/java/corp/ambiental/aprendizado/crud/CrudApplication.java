package corp.ambiental.aprendizado.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "corp.ambiental.aprendizado.crud")
public class CrudApplication {
	public static void main(String[] args){
		SpringApplication.run(CrudApplication.class, args);

	}
}
