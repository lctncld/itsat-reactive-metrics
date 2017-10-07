package com.epam.itsat.reactive.metrics;

import com.epam.itsat.reactive.metrics.health.HealthCheckService;
import com.epam.itsat.reactive.metrics.health.RequestMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMetricsApplication.class, args);
	}

	@RestController
	public class HealthRestController {

		private final HealthCheckService service;

		public HealthRestController(HealthCheckService service) {
			this.service = service;
		}

		@GetMapping(value = "/requestMetrics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
		public Flux<RequestMetrics> requestMetrics() {
			return service.getRequestMetrics();
		}
	}
}
