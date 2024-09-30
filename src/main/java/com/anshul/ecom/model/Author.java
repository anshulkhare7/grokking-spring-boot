package com.anshul.ecom.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("author")
public class Author implements Serializable {

    private String id;
    private String firstName;
    private String lastName;

    private List<Book> books;

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ((null != books) ? ", books=" + books.stream().map(i -> i.getName()).collect(Collectors.toList()) + '\'' : "") +
                '}';
    }
}
