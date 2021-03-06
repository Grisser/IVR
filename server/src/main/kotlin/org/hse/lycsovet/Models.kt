package org.hse.lycsovet

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*

enum class Stream {
        MATHINFO, MATHEC, MATH, SOCEC, HUM, PSYSOC, LAW, ORIENTAL, DESIGN, SCIENCE, FUTURITET, UNKNOWN
}

@Entity
@Table(name = "appeal_types")
data class AppealType(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,

        val name: String
)

@Entity
@Table(name = "appeal_statuses")
data class AppealStatus(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,

        val name: String,
        val milestone: Long
)

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        @Column(unique = true)
        val name: String,
        @JsonIgnore
        val level: Int
)

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,

        @Column(unique = true)
        val login: String,
        @ManyToOne
        var role: Role,
        val grade: Int,
        @Enumerated(EnumType.STRING)
        val stream: Stream,
        val name: String,
        var actual: Boolean = false,
        @JsonIgnore
        val created: Date = Date(),
        @JsonIgnore
        var updated: Date = Date()
)

@Entity
@Table(name = "articles")
data class Article(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        var title: String,
        @Column(columnDefinition = "text")
        var description: String,

        @Column(columnDefinition = "text")
        var text: String,
        var published: Boolean,
        val created: Date = Date(),
        var updated: Date = Date()
)

@Entity
@Table(name = "appeals")
data class Appeal(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,

        var title: String,
        @Column(columnDefinition = "text")
        var text: String,
        @ManyToOne
        val author: User,
        val feedback: Int,
        var published: Boolean,
        @ManyToOne
        var type: AppealType,
        @ManyToOne
        var status: AppealStatus,
        val created: Date = Date(),
        var updated: Date = Date()
)

@Entity
@Table(name = "tickets")
data class Ticket(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        @ManyToOne
        val user: User,
        @Column(columnDefinition = "text")
        val text: String,
        @Column(columnDefinition = "text")
        var response: String = "",
        var opened: Boolean = true,
        val created: Date = Date(),
        var updated: Date = Date()
)