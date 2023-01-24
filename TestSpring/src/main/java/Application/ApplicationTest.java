package Application;

import Application.module.RetryModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationTest {

    public static void main(String args[]) {
        ApplicationContext applicationContext = SpringApplication.run(ApplicationTest.class, args);
        RetryModule retryModule = applicationContext.getBean(RetryModule.class);
        try {
            retryModule.doStuff(5, 3);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }


}
