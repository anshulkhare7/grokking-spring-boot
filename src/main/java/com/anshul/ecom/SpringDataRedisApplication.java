package com.anshul.ecom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class SpringDataRedisApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringDataRedisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisApplication.class, args).close();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        //set the String serializer for key and value
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());    

        //Redis Transactions
        redisTemplate.setEnableTransactionSupport(true);

        RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
        
        //execute a transaction
        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                //invoke redis multi command
                operations.multi();

                ValueOperations<String, String> valueOps = operations.opsForValue();
                valueOps.set("book1", "Martian");
                valueOps.set("book2", "Atomic Habits");
                valueOps.increment("book3", 1);

                //invoke redis exec command
                return operations.exec();
            }
        };

        //execute the sessionCallback
        redisOperations.execute(sessionCallback);

        //log the values
        log.info(String.valueOf(redisOperations.opsForValue().get("book1")));
        log.info(String.valueOf(redisOperations.opsForValue().get("book2")));
        log.info(String.valueOf(redisOperations.opsForValue().get("book3")));
    }
}
