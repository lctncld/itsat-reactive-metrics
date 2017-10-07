package com.epam.itsat.reactive.metrics.health;

import com.epam.itsat.reactive.metrics.health.counter.RequestCounterService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
class ReactorHealthCheckService implements HealthCheckService {

	private final RequestCounterService counter;

	public ReactorHealthCheckService(RequestCounterService counter) {
		this.counter = counter;
	}

	@Override
	public Flux<RequestMetrics> getRequestMetrics() {
		Flux<Long> allRequests = Flux.generate(sink -> sink.next(counter.getTotalRequestCount()));
		Flux<Long> failedRequests = Flux.generate(sink -> sink.next(counter.getFailedRequestCount()));
		return Flux.zip(allRequests, failedRequests)
				.map(tuple -> RequestMetrics.builder()
						.total(tuple.getT1())
						.failed(tuple.getT2())
						.timestamp(LocalDateTime.now())
						.build()
				);
	}
}