package com.example.library.library_service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
@Table(name = "ReturnRecord")
class ReturnRecord(
    @Id
    @Column
    var id: String = "",
    @CreationTimestamp
    @Column(name = "create_time")
    var createTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "total_late_fee")
    var totalLateFee: BigDecimal = BigDecimal.ZERO,
    @Column(name = "total_defect_fee")
    var totalDefectFee: BigDecimal = BigDecimal.ZERO,
    var tax: BigDecimal = BigDecimal.ZERO,
    @Column(name = "grand_fee")
    var grandFee: BigDecimal = BigDecimal.ZERO,
    @Column(name = "is_paid")
    var isPaid: Boolean = false,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    @JsonIgnore
    var reader: User? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id")
    @JsonIgnore
    var librarian: User? = null


    @OneToMany(mappedBy = "returnRecord", fetch = FetchType.LAZY)
    @JsonIgnore
    var returnedBorrowBooks: MutableList<BorrowBook> = mutableListOf()
}