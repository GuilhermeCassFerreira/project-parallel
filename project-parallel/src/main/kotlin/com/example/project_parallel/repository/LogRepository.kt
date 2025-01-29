package com.example.project_parallel.repository

import com.example.project_parallel.entity.Log
import org.springframework.data.jpa.repository.JpaRepository

interface LogRepository : JpaRepository<Log, Long>