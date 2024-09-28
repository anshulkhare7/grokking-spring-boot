package com.anshul.ecom.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "AUTHOR_BOOK")
public class AuthorBook {

    @Column("AUTHOR_ID")
    private long authorId;

    @Column("BOOK_ID")
    private long bookId;

    @Override
    public String toString() {
        return "AuthorBook{" +
                "authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }
}