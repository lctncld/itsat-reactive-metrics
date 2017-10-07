package com.epam.itsat.reactive.metrics.health;

import reactor.core.publisher.Flux;

public interface HealthCheckService {

	Flux<RequestMetrics> getRequestMetrics();
}
