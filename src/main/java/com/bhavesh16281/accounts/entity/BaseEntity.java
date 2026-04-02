package com.bhavesh16281.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at",updatable = false)
    private LocalDateTime cretedAt;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "updated_at",insertable = false,updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by",insertable = false,updatable = false)
    private String updatedBy;
}
