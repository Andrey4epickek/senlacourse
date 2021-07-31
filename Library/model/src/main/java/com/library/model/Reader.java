package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Readers")
public class Reader extends AEntity{

    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Patronymic")
    private String patronymic;
    @Column(name = "Age")
    private Integer age;
    @Column(name = "Data")
    private LocalDate data;
    @OneToMany(mappedBy = "reader",fetch = FetchType.LAZY)
    private List<Issuance> issuance;
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<Acceptance> acceptance;

    @Override
    public String toString() {
        return "Reader{id=" +getId()+
                ", lastName=" + lastName +
                ", firstname=" + firstName +
                ", patronymic=" + patronymic +
                ", age=" + age +
                ", data=" + data +
                "}";
    }
}
