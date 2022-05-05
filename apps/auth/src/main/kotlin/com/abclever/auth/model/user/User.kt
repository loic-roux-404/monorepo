package com.abclever.auth.model.user

import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.validation.annotation.Validated
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import java.sql.Date

@Data
@Entity
@Validated
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val firstName : String? = null,

    @Column
    val lastName: String? = null,

    @Column(nullable = false)
    val password: String? = null,

    @Column
    val picture: String? = null,

    @Email
    @Column(nullable = false)
    val email: String? = null,

    @Email
    @Column(nullable = false)
    val emailVerified: String? = null,

    @Column
    val birthdate: Date? = null,

    @Column
    @Min(0)
    @Max(1)
    val gender: Int? = null,

    @CreationTimestamp
    @Column
    val createdAt: Date? = null,

    @UpdateTimestamp
    @Column
    val updatedAt: Date? = null
)
