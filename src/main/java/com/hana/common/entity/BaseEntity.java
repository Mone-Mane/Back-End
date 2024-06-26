package com.hana.common.entity;

import com.hana.common.type.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false, name = "created_date")
    protected LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @ColumnDefault("'ACTIVE'")
    private State state;

    @PrePersist
    public void prePersist() {
        if (this.state == null) {
            this.state = State.Active;
        }
    }
}
