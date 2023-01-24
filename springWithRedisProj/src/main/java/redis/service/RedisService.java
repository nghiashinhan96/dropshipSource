package redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void saveRedis() throws Exception{
        redisTemplate.opsForValue().set("name","nghia");
    }
    public  void deleteKey(String keyName){
        try{
            redisTemplate.delete(keyName);
        }catch(Exception ex){

        }
    }
    public  String getStringValueFromRedis(String keyName) {
        String result = null;
        try {

            if (keyName != null && !keyName.isBlank()) {
                result = redisTemplate.opsForValue().get(keyName).toString();
            } else {
                System.out.println("key name can't be nul");

            }

        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                System.out.println("Not found Key");
            }
            ex.printStackTrace();
        }
        return result;

    }
}
