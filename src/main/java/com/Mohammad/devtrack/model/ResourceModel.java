package com.Mohammad.devtrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;


@Entity
@Table(name = "Resources")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RID;


    @ManyToOne(optional = false)
    @JoinColumn(name = "U_ID", nullable = false)
    private UserModel UID;

    @Column(name = "CompanyName", nullable = false) //
    private String CompanyName;

    @Column(name = "DateAppliedTo", nullable = true) //
    private LocalDateTime DateAppliedTo;

    @Column(name = "JobPostingURI", nullable = true) //
    private String JobPostingURI;

    @Column(name = "Notes", nullable = true) //
    private String Notes;

    @Column(name = "DateCreated", nullable = true)
    private LocalDateTime DateCreated;

    @Column(name = "LastUpdated", nullable = true)
    private LocalDateTime LastUpdated;
    
    @Column(name = "Salary", nullable = false)
    private int Salary;
    
    
}
