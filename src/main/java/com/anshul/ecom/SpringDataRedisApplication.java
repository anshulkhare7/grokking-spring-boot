package com.anshul.ecom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
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
        //set String serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        //Redis Streams
        String streamKey = "bookStream";
        String consumerGroupKey = "educativeGroup";
        String consumerNameKey = "educativeConsumer";
        List<String> books = Arrays.asList(new String[]{"Martian", "Atomic Habits", "The Psychology Of Money",
                "Project Hail Mary", "Zero To One"});

        StreamOperations redisStreamOperator = redisTemplate.opsForStream();
        
        for (int i = 0; i < books.size(); i++) {
            Map bookMap = new HashMap<String, String>();
            bookMap.put("book", books.get(i));

            //appending
            StringRecord record = StreamRecords.string(bookMap).withStreamKey(streamKey);
            redisStreamOperator.add(record);
        }

        Consumer educativeConsumer = Consumer.from(consumerGroupKey, consumerNameKey);
        StreamReadOptions readOptions = StreamReadOptions.empty();
        StreamOffset streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());
        ReadOffset readOffset = ReadOffset.from("0-0");

        //create group if it doesn't exist
        if (redisStreamOperator.groups(streamKey).isEmpty()) {
            redisStreamOperator.createGroup(streamKey, readOffset, consumerGroupKey);
        }

        //synchronous read
        List<MapRecord> messages = redisStreamOperator.read(educativeConsumer, readOptions.count(2), streamOffset);
        log.info(String.valueOf(messages));

        //acknowledge the message
        messages.forEach(map -> {
            redisStreamOperator.acknowledge(streamKey, consumerGroupKey, map.getId());
        });

        //read again from the last consumed
        messages = redisStreamOperator.read(educativeConsumer, readOptions.count(2), streamOffset);
        log.info(String.valueOf(messages));
    }
}
