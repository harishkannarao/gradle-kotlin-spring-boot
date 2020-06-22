package com.harishkannarao.springboot.kotlin.demo.service

import com.harishkannarao.springboot.kotlin.demo.dao.CustomerRepository
import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CustomerServiceUnitTest {
    private lateinit var mockCustomerRepository: CustomerRepository
    private lateinit var customerService: CustomerService

    @BeforeEach
    internal fun setUp() {
        mockCustomerRepository = mock(CustomerRepository::class.java)
        customerService = CustomerService(mockCustomerRepository)
    }

    @Test
    internal fun `return customer by id from repository`() = runBlocking {
        val expected = CustomerResponseDto(
                id = "test-id",
                firstName = "firstName",
                lastName = "lastName"
        )
        `when`(mockCustomerRepository.getById("test-id")).thenReturn(expected)
        val result = customerService.getById("test-id")
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `return all customers from repository`() = runBlocking {
        val expected = listOf<CustomerResponseDto>(
                CustomerResponseDto(
                        id = "test-id",
                        firstName = "firstName",
                        lastName = "lastName"
                )
        )
        `when`(mockCustomerRepository.getAll()).thenReturn(expected.asFlow())
        val result = customerService.getAll()
        assertThat(result.toList(), containsInAnyOrder(*expected.toTypedArray()))
    }
}