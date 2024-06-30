package com.durgesh.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String firstName;
    private String middleName;
    private String lastName;

    @Column(name = "user_role", nullable = false, length = 50)
    private String userRole;

    @Column(name = "email", unique = true, nullable = false, length = 80)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 50)
    private String phone;

    private String about;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTs;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTs;
}