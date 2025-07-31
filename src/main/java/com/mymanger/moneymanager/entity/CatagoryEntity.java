package com.mymanger.moneymanager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name ="tbl_catagories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatagoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String type;//income or expence

    private String icon;

    //who created catagory
    @ManyToOne(fetch = FetchType.LAZY)       //many catagories belongs to same profile  lazy->untill getProfile called profile will be null
    @JoinColumn(name ="profile_id",nullable = false)  //extra column name is profile_id and it is not null value
    private ProfileEntity profile;


}
