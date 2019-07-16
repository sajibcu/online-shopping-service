package com.red.code.onlineshopping.database.entity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;


    @Email
    @Size(max = 50)
    @Column(length = 50, nullable = false, updatable = false, unique = true)
    private String email;

    @Size(max = 25)
    @Column(name = "first_name", length = 25)
    private String firstName;

    @Size(max = 25)
    @Column(name = "last_name", length = 25)
    private String lastName;

    @Size(min = 60, max = 60)
    @Column(length = 60, nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "user_role", nullable = true)
    private Integer userRole;

}
