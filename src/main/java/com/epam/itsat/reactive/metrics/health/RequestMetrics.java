package com.epam.itsat.reactive.metrics.health;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestMetrics {

	private final Long total;

	private final Long failed;

	private final LocalDateTime timestamp;
}
