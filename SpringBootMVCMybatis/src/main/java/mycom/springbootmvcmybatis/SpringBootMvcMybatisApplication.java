package mycom.springbootmvcmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mycom.springbootmvcmybatis.emp")
public class SpringBootMvcMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcMybatisApplication.class, args);
    }

}
