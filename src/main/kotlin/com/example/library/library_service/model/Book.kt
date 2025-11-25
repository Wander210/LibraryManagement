package com.example.library.library_service.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class Book(
    @Id
    @Column(length = 255)
    var id: String = "",
    @Column(length = 500)
    var title: String = "",
    var category: String = "",
    var author: String = "",
    var publisher: String = "",
    @Column(name = "publication_year")
    var publicationYear: Int = 0,
    @Lob
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
    open var photoUrls: MutableList<PhotoUrl> = mutableListOf()


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    open var borrowBookItems: MutableList<BorrowBook> = mutableListOf()
}