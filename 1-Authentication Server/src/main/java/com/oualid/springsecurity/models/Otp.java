package com.oualid.springsecurity.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ins_otp")
@Data
public class Otp {

    @Id
    @SequenceGenerator(name = "SEQ_OTP_GEN",sequenceName = "SEQ_OTP",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_OTP_GEN")
    @Column(name = "otp_id",unique = true,nullable = false)
    private Long id;

    private String username;
    private String code;
}
