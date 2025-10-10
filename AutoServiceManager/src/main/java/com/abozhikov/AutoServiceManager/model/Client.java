package com.abozhikov.AutoServiceManager.model;

import jakarta.persistence.Entity;
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
    private int id;
    @Getter
    private String firstName;
    private String lastName;
    private String email;
    private int phone;

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lasstName) {
        this.lastName=lasstName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(int phone) {
        this.phone=phone;
    }
}
