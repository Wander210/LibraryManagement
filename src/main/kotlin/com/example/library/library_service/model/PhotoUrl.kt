package com.example.library.library_service.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class PhotoUrl(
    @Id
    @Column
    var id: String = "",
    var url: String = "",
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    var book: Book? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    var defect: Defect? = null
}