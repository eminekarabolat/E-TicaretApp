package org.example.eticaretapp.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.enums.State;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    State state = State.ACTIVE;
    @Builder.Default
    Long createAt = System.currentTimeMillis();
    @Builder.Default
    Long updateAt = System.currentTimeMillis();
    Long deleteAt;

}
