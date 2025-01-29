package com.example.project_parallel.repository

import com.example.project_parallel.entity.Process
import org.springframework.data.jpa.repository.JpaRepository

interface ProcessRepository : JpaRepository<Process, Long>