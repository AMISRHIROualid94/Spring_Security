package com.oualid.springsecurity.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ins_user")
@Data
public class User {

    @Id
    @SequenceGenerator(name = "SEQ_USER_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USER_GEN",strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
