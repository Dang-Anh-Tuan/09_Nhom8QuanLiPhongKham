package qlpk.entity;

import lombok.Data;
import qlpk.entity.enums.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Column(name = "user_name", unique = true)
    private String userName;
}
