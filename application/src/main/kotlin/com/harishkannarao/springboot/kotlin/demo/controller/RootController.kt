package com.harishkannarao.springboot.kotlin.demo.controller

import com.harishkannarao.springboot.kotlin.demo.service.RootService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class RootController(
        private val rootService: RootService
) {
    @GetMapping
    suspend fun success(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok().body(rootService.getEntity())
    }
}