package com.Mohammad.devtrack.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
// No argument constructor for JPA
@NoArgsConstructor
@AllArgsConstructor 
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;
    
    @Column(name = "UName", nullable = false)
    private String UName;
    
    @Column(name = "UEmail", nullable = false)
    private String UEmail;
    
    @Column(name = "UHashedPassword", nullable = false)
    private String UHashedPassword;
    
    @Column(name = "UCreatedOn")
    private LocalDateTime UCreatedOn;

    @OneToMany(mappedBy = "UID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ResourceModel> resources;
}
