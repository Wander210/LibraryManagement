package com.example.library.library_service.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class BorrowBook(
    @Id
    @Column
    var id: String = "",
    @Column(name = "return_time")
    var returnTime: LocalDateTime? = null,
    @Column(name = "days_overdue")
    var daysOverdue: Int = 0,
    @Column(name = "late_fee")
    var lateFee: BigDecimal = BigDecimal.ZERO,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    var book: Book? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_record_id", nullable = false)
    @JsonIgnore
    var borrowRecord: BorrowRecord? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_record_id")
    @JsonIgnore
    var returnRecord: ReturnRecord? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defect_id")
    @JsonIgnore
    var defect: Defect? = null
}