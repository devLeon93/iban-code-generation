package com.icg.icgbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "created_at")
    protected LocalDateTime createdAt;
    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onModified() {
        this.modifiedAt = LocalDateTime.now();
    }

}
