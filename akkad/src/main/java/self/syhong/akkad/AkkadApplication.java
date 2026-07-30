package self.syhong.akkad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("self.syhong.akkad.domain.mapper")
public class AkkadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkadApplication.class, args);
	}

}
