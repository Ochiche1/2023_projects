package com.faithproject.app.dto;

public record CustomerRequestRegistration(
    String firstName,
    String lastName,
    String email
) {
}
