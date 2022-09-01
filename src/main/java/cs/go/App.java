package cs.go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.transaction.Transactional;


@SpringBootApplication
@Transactional
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
