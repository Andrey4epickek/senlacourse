package com.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles")
@Data
public class Role extends AEntity{

    @Column(name = "Name")
    private String name;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{id=" +getId()+
                ", name=" + name +
                "}";
    }
}
