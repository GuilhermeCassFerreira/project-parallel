package com.example.project_parallel.service

import com.example.project_parallel.entity.Log
import com.example.project_parallel.entity.Process
import com.example.project_parallel.repository.LogRepository
import com.example.project_parallel.repository.ProcessRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

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
            val commands = process.command.split(";")
            if (process.parallel) {
                commands.forEach { command ->
                    launch {
                        executeCommand(command, process)
                    }
                }
            } else {
                commands.forEach { command ->
                    executeCommand(command, process)
                }
            }

            process.endTime = System.currentTimeMillis()
            process.status = "COMPLETED"
            processRepository.save(process)
        }
    }

    private fun executeCommand(command: String, process: Process) {
        try {
            val processBuilder = ProcessBuilder(command.split(" "))
            processBuilder.redirectErrorStream(true)
            val processInstance = processBuilder.start()
            val reader = BufferedReader(InputStreamReader(processInstance.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val log = Log(process = process, timestamp = System.currentTimeMillis(), message = line!!)
                logRepository.save(log)
                messagingTemplate.convertAndSend("/topic/logs", log)
            }
            val exitCode = processInstance.waitFor()
            val log = Log(process = process, timestamp = System.currentTimeMillis(), message = "Exited with code: $exitCode")
            logRepository.save(log)
            messagingTemplate.convertAndSend("/topic/logs", log)
        } catch (e: Exception) {
            val log = Log(process = process, timestamp = System.currentTimeMillis(), message = "Error: ${e.message}")
            logRepository.save(log)
            messagingTemplate.convertAndSend("/topic/logs", log)
        }
    }
}