package com.example.project_parallel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.SpringApplication;
@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.example.project_parallel.repository"])
@EntityScan(basePackages = ["com.example.project_parallel.entity"])
class ProjectParallelApplication

fun main(args: Array<String>) {
    runApplication<ProjectParallelApplication>(*args)
}