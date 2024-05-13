package fsoft.tanvx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@SuperBuilder(toBuilder = true)
@Setter
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "delete_flg")
    private Boolean deleteFlg;

    @CreatedDate
    @Column(name = "created_at")
    @JsonFormat(shape = Shape.STRING)
    private Instant createdAt;

    @CreatedBy
    @Column(name = "created_by", length = 36)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonFormat(shape = Shape.STRING)
    private Instant updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", length = 36)
    private String updatedBy;
}
