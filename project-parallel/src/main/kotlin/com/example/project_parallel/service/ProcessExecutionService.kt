package com.example.project_parallel.service

import com.example.project_parallel.entity.Log
import com.example.project_parallel.entity.Process
import com.example.project_parallel.repository.LogRepository
import com.example.project_parallel.repository.ProcessRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ProcessExecutionService(
    val processRepository: ProcessRepository,
    val logRepository: LogRepository,
    val messagingTemplate: SimpMessagingTemplate
) {

    fun executeProcess(process: Process) {
        GlobalScope.launch {
            process.startTime = System.currentTimeMillis()
            process.status = "RUNNING"
            processRepository.save(process)

            // Simular execução de tarefas
            for (i in 1..process.tasks) {
                delay(process.estimatedTime / process.tasks)
                val log = Log(process = process, timestamp = System.currentTimeMillis(), message = "Task $i completed")
                logRepository.save(log)
                messagingTemplate.convertAndSend("/topic/logs", log)
            }

            process.endTime = System.currentTimeMillis()
            process.status = "COMPLETED"
            processRepository.save(process)
        }
    }
}