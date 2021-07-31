package com.library.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User extends AEntity{

    @Column(name = "Username",unique = true)
    private String username;
    @Column(name = "Password")
    private String password;
    @CreatedDate
    @Column(name = "Created")
    private Date created;
    @LastModifiedDate
    @Column(name = "Updated")
    private Date updated;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{id=" +getId()+
                ", username=" + username +
                ", password=" + password +
                ", roles=" + roles +
                "}";
    }

}
