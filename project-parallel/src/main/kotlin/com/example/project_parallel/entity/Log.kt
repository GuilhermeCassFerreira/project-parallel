package com.example.project_parallel.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn

@Entity
data class Log(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "process_id")
    val process: Process,
    val timestamp: Long,
    val message: String
)