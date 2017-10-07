package com.epam.itsat.reactive.metrics.health;

import com.epam.itsat.reactive.metrics.health.counter.RequestCounterService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
class ReactorHealthCheckService implements HealthCheckService {

	private final RequestCounterService counter;

	public ReactorHealthCheckService(RequestCounterService counter) {
		this.counter = counter;
	}

	@Override
	public Flux<RequestMetrics> getRequestMetrics() {
		return Flux.empty();
	}
}