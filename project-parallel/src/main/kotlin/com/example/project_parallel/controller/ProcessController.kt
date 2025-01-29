package com.example.project_parallel.controller

import com.example.project_parallel.entity.Process
import com.example.project_parallel.service.ProcessService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api/process")
class ProcessController(val processService: ProcessService) {

    @PostMapping("/create")
    fun createProcess(@RequestBody process: Process): Process {
        return processService.createProcess(process)
    }

    @GetMapping("/list")
    fun listProcesses(): List<Process> {
        return processService.listProcesses()
    }
}