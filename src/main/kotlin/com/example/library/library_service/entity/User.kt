package com.example.library.library_service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.PostLoad
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "User")
class User(
    @Id
    @Column
    var id: String = "",
    @Column(nullable = false, unique = true)
    var username: String = "",
    var password: String = "",
    var fullName: String = "",
    var role: String,
    var email: String,
    var phone: String,
    var address: String,
    @Column(name = "black_list_flag")
    var blackListFlag: Boolean? = false,
    @Column(name = "hire_date")
    var hireDate: LocalDate,
    var position: String,
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
        @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        var borrowRecords: MutableList<BorrowRecord> = mutableListOf()


    @OneToMany(mappedBy = "librarian", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    var processedBorrows: MutableList<BorrowRecord> = mutableListOf()
    
    @PostLoad
    fun ensureBlackListFlagNotNull() {
        // Đảm bảo blackListFlag không bao giờ null (fix cho database có thể có NULL)
        if (blackListFlag == null) {
            blackListFlag = false
        }
    }
    
    // Getter để luôn trả về Boolean (không null)
    fun getBlackListFlagValue(): Boolean = blackListFlag ?: false
}