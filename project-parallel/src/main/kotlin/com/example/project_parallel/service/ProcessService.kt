package com.example.project_parallel.service

import com.example.project_parallel.entity.Process
import com.example.project_parallel.repository.ProcessRepository
import org.springframework.stereotype.Service

@Service
class ProcessService(val processRepository: ProcessRepository) {

    fun createProcess(process: Process): Process {
        return processRepository.save(process)
    }

    fun listProcesses(): List<Process> {
        return processRepository.findAll()
    }
}