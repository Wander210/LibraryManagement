package com.example.library.library_service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "BorrowRecord")
class BorrowRecord(
    @Id
    @Column
    var id: String = "",
    var quantity: Int = 0,
    @Column(name = "borrow_time")
    var borrowTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "due_time")
    var dueTime: LocalDateTime = LocalDateTime.now() + java.time.Duration.ofDays(1),
    @Column(name = "initial_total")
    var initialTotal: BigDecimal = BigDecimal.ZERO,
    var discount: BigDecimal = BigDecimal.ZERO,
    var tax: BigDecimal = BigDecimal.ZERO,
    @Column(name = "grand_total")
    var grandTotal: BigDecimal = BigDecimal.ZERO,
    @Column(name = "is_paid")
    var isPaid: Boolean = false,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    @JsonIgnore
    var reader: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id")
    @JsonIgnore
    var librarian: User? = null


    @OneToMany(mappedBy = "borrowRecord", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    open var borrowBooks: MutableList<BorrowBook> = mutableListOf()
}