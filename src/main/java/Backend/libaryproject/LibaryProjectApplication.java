package Backend.libaryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Backend.libaryproject.Repository"})
@EnableDiscoveryClient
public class LibaryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibaryProjectApplication.class, args);
    }

}
