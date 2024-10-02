package com.anshul.ecom;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
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

        //string operations
        redisTemplate.opsForValue().set("Book", "Author");

        log.info(String.valueOf(redisTemplate.hasKey("Book")));
        log.info(String.valueOf(redisTemplate.opsForValue().get("Book")));

        //list operator
        ListOperations redisListOperator = redisTemplate.opsForList();

        //list operations
        redisListOperator.rightPush("BookList", "Atomic Habits");
        redisListOperator.rightPush("BookList", "Martian");
        redisListOperator.rightPush("BookList", "The Psychology Of Money");
        redisListOperator.rightPush("BookList", "Zero To One");

        log.info(String.valueOf(redisTemplate.hasKey("BookList")));
        log.info(String.valueOf(redisListOperator.size("BookList")));
        log.info(String.valueOf(redisListOperator.index("BookList", 1)));
        log.info(String.valueOf(redisListOperator.range("BookList", 2, 3)));

        redisListOperator.set("BookList", 1, "Project Hail Mary");
        log.info(String.valueOf(redisListOperator.index("BookList", 1)));

        //set operator
        SetOperations redisSetOperator = redisTemplate.opsForSet();

        //set operations
        redisSetOperator.add("BookSet", "Atomic Habits", "Martian", "The Psychology Of Money", "Leaders eat last");

        log.info(String.valueOf(redisSetOperator.size("BookSet")));

        log.info(String.valueOf(redisSetOperator.isMember("BookSet", "Martian")));
        log.info(String.valueOf(redisSetOperator.isMember("BookSet", "Zero To One")));

        log.info(redisSetOperator.members("BookSet").toString());

        redisSetOperator.remove("BookSet", "Martian");

        log.info(redisSetOperator.members("BookSet").toString());

        //set the String serializer for Hash key and value
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        //Hash operator
        HashOperations redisHashOperator = redisTemplate.opsForHash();

        //Hash operations
        Map thePsychologyOfMoney = new HashMap<String, String>();
        thePsychologyOfMoney.put("id", "thePsychologyOfMoney");
        thePsychologyOfMoney.put("name", "The Psychology of Money");
        thePsychologyOfMoney.put("summary", "Timeless Lessons on Wealth, Greed, and Happiness");

        redisHashOperator.putAll("Book" + thePsychologyOfMoney.get("id"), thePsychologyOfMoney);

        log.info(String.valueOf(redisHashOperator.entries("Book" + thePsychologyOfMoney.get("id"))));

        // HashMapper<Book, String, String> bookHashMapper = new DecoratingStringHashMapper<>(new PojoHashMapper<>(Book.class));
        // log.info(String.valueOf(bookHashMapper.fromHash(redisHashOperator.entries("Book" + thePsychologyOfMoney.get("id")))));
    }
}
