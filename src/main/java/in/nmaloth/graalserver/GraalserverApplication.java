package in.nmaloth.graalserver;

import org.apache.geode.internal.cache.tx.RemoteClearMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraalserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalserverApplication.class, args);
	}
}
