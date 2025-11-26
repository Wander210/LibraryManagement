package com.example.library.library_service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "Defect")
class Defect(
    @Id
    @Column(length = 255)
    var id: String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "defect_status")
    var status: DefectStatus = DefectStatus.REPORTED,
    @Enumerated(EnumType.STRING)
    var severity: Severity = Severity.LOW,
    @Column(name = "defect_reason")
    var defectReason: String = "",
    @Column(name = "defect_fee")
    var defectFee: BigDecimal = BigDecimal.ZERO,
    @Column(name = "resolved_date")
    var resolvedDate: LocalDate? = null,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
) {
    @OneToMany(mappedBy = "defect", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    var attachments: MutableList<PhotoUrl> = mutableListOf()

    @OneToMany(mappedBy = "defect", fetch = FetchType.LAZY)
    @JsonIgnore
    var relatedBorrowBooks: MutableList<BorrowBook> = mutableListOf()
}

enum class DefectStatus {
    REPAIRED,
    REPORTED,
    IN_REPAIR
}

enum class Severity {
    LOW,
    MEDIUM,
    HIGH
}