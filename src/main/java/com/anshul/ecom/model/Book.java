package com.anshul.ecom.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("book")
public class Book implements Serializable {

    private String id;
    private String name;
    private String summary;

    private List<Author> authors;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ((null != authors) ? ", authors=" + authors.stream().map(i -> i.getFullName()).collect(Collectors.toList()) + '\'' : "") +
                '}';
    }

}
