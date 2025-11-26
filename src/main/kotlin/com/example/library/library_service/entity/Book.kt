package com.example.library.library_service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "Book")
class Book(
    @Id
    @Column
    var id: String = "",
    @Column
    var title: String = "",
    var category: String = "",
    var author: String = "",
    var publisher: String = "",
    @Column(name = "publication_year")
    var publicationYear: Int = 0,
    var description: String = "",
    @Column(name = "available_copies")
    var availableCopies: Int = 0,
    @Column(name = "total_of_copies")
    var totalOfCopies: Int = 0,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
) {
    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    var photoUrls: MutableList<PhotoUrl> = mutableListOf()


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    var borrowBookItems: MutableList<BorrowBook> = mutableListOf()
}