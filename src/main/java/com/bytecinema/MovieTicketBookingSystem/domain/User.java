package com.bytecinema.MovieTicketBookingSystem.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.bytecinema.MovieTicketBookingSystem.util.constant.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
@Entity
@Table(name="Users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Column(name="gender")
    private GenderEnum gender;
    private String phoneNumber;
    private String avatar;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDay;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String refreshToken;
    private Instant createAt;
    private Instant updateAt;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String otp;
    private Instant expirationTime;
    private boolean verified;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String token;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;


    @PrePersist
    public void handleBeforeCreated(){
      
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updateAt = Instant.now();
    }


    
}
