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

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
// No argument constructor for jpa
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @Column(name = "UName", nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "UEmail", nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "UHashedPassword", nullable = false)
    // Won't allow for it to be returned in json format during the request
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String UHashedPassword;

    @Column(name = "UCreatedOn")
    private LocalDateTime UCreatedOn;

    @OneToMany(mappedBy = "UID", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ResourceModel> resources;

}