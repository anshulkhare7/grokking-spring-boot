package com.anshul.ecom.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name = "AUTHOR")
@Data
public class Author {

    @Id
    private long id;

    @Column("FIRST_NAME")
    private String firstName;

    @Column("LAST_NAME")
    private String lastName;

    @MappedCollection(idColumn = "AUTHOR_ID")
    private Set<AuthorBook> books = new HashSet<>();

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books.stream().mapToLong(i -> i.getBookId()).boxed().toList().toString() +
                '}';
    }
}