package selly;

import org.openqa.selenium.ElementClickInterceptedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import selly.service.FunnimartService;
import selly.service.Utilities;

@SpringBootApplication
public class FunnimartAutoDropship {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FunnimartAutoDropship.class, args);
        FunnimartService funnimartService = context.getBean(FunnimartService.class);
        try {
            funnimartService.setupDriver();
            funnimartService.loginFunnimart("chrome", "https://funimart.vn/login", "nghiashinhan96@gmail.com", "Shinhan@1");
            funnimartService.gotoShopPage();
            for(int i =0;i <5;i++){
                funnimartService.gotoSellyHomePageAndSearch("thoi trang nam");
                try{
                    Utilities.sleep(300);
                    funnimartService.serviceGetItemInfo(i);
                }catch (ElementClickInterceptedException ex){continue;}

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
