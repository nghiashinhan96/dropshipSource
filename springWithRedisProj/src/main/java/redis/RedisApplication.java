package redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import redis.service.RedisService;


@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        RedisService service = context.getBean(RedisService.class);
        try {
            service.saveRedis();
            //service.deleteKey("name");
            String name = service.getStringValueFromRedis("name");
            System.out.print("sucess save redis: "+name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
