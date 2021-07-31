package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Workers")
public class Worker extends AEntity{

    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Patronymic")
    private String patronymic;
    @Column(name = "Data")
    private LocalDate data;
    @OneToMany(mappedBy = "worker",fetch = FetchType.LAZY)
    private List<Issuance> issuance;
    @OneToMany(mappedBy = "worker",fetch = FetchType.LAZY)
    private List<Acceptance> acceptance;

    @Override
    public String toString() {
        return "Worker{id=" +getId()+
                ", lastName=" + lastName +
                ", firstname=" + firstName +
                ", patronymic=" + patronymic +
                ", data=" + data +
                "}";
    }
}
