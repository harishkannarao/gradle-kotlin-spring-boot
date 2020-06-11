package com.harishkannarao.springboot.kotlin.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class RootController {
    @GetMapping
    fun success(): ResponseEntity<Map<String, String>> {
        val entity = mapOf(
                Pair("message", "success")
        )
        return ResponseEntity.ok().body(entity)
    }
}