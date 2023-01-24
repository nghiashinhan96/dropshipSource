package selly;

import org.openqa.selenium.ElementClickInterceptedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import selly.service.SellyService;

@SpringBootApplication
public class SellyAutoDropship {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SellyAutoDropship.class, args);
        SellyService sellyService = context.getBean(SellyService.class);
        try {
            sellyService.setupDriver();
            sellyService.loginSelly("chrome", "https://selly.vn/login", "0586099640", "123456");
            sellyService.closeAdvertisement();

            for(int i =0;i <19;i++){
                sellyService.gotoSellyHomePageAndSearch("thoi trang nam");
                try{
                    sellyService.serviceGetItemInfo(i);
                }catch (ElementClickInterceptedException ex){continue;}

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
