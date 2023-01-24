package selly;

import org.openqa.selenium.ElementClickInterceptedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import selly.service.ShopeeService;
import selly.service.Utilities;

@SpringBootApplication
public class ShopeeAutoDropship {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopeeAutoDropship.class, args);
        ShopeeService shopeeService = context.getBean(ShopeeService.class);
        try {
            shopeeService.setupDriver();
            shopeeService.loginShopee("chrome", "https://banhang.shopee.vn/account/signin?next=%2F", "0586099640", "S301w5j2");
            Utilities.sleep(3000);//waiting accept sms
            shopeeService.closeAdvertisement();
            //shopee-popup__close-btn
            shopeeService.addPro();
            shopeeService.putProductImages();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
