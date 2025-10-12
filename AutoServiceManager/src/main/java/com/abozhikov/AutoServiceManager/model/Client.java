package com.abozhikov.AutoServiceManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;


    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone=phone;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
