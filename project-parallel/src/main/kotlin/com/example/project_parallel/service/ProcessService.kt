package com.example.project_parallel.service

import com.example.project_parallel.entity.Process
import com.example.project_parallel.entity.Log
import com.example.project_parallel.repository.ProcessRepository
import com.example.project_parallel.repository.LogRepository
import org.springframework.stereotype.Service

@Service
class ProcessService(
    val processRepository: ProcessRepository,
    val logRepository: LogRepository,
    val processExecutionService: ProcessExecutionService
) {

    fun createProcess(process: Process): Process {
        val savedProcess = processRepository.save(process)
        processExecutionService.executeProcess(savedProcess)
        return savedProcess
    }

    fun listProcesses(): List<Process> {
        return processRepository.findAll()
    }

    fun getProcessStatus(id: Long): Process {
        return processRepository.findById(id).orElseThrow { RuntimeException("Process not found") }
    }

    fun getProcessLogs(id: Long): List<Log> {
        return logRepository.findByProcessId(id)
    }
}