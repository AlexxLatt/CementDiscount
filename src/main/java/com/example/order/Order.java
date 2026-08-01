package com.example.order;

import java.time.LocalDateTime;

public record Order(LocalDateTime date, String companyName, Double amount) {}