package com.library.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
}
