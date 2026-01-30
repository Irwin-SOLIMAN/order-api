package com.irwin.order_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequestDTO(
        @Email(message = "e-mail must be filled") String email,
        @NotBlank(message = "firstName must be filled") String firstName,
        @NotBlank(message = "lastName must be filled") String lastName
) {}
