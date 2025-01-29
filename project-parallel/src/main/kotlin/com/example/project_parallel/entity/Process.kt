package com.example.project_parallel.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class Process(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val estimatedTime: Long,
    val parallel: Boolean,
    val tasks: Int,
    var status: String = "PENDING",
    var startTime: Long? = null,
    var endTime: Long? = null
)