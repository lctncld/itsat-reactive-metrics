package com.epam.itsat.reactive.metrics.health.counter;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.NamingConvention;
import io.micrometer.core.instrument.Statistic;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
class RequestCounterActuatorService implements RequestCounterService {

	private final MeterRegistry registry;

	public RequestCounterActuatorService(MeterRegistry registry) {
		this.registry = registry;
	}

	@Override
	public long getFailedRequestCount() {
		return registry.find("http.server.requests").meters()
				.stream()
				.filter(meter -> meter.getId().getConventionTags(NamingConvention.camelCase)
						.stream()
						.anyMatch(tag -> tag.getKey().equals("status") && tag.getValue().startsWith("4")))
				.flatMap(meter -> StreamSupport.stream(meter.measure().spliterator(), false))
				.filter(measurement -> measurement.getStatistic() == Statistic.Count)
				.map(Measurement::getValue)
				.mapToLong(Double::longValue)
				.sum();
	}

	@Override
	public long getTotalRequestCount() {
		return registry.find("http.server.requests").meters()
				.stream()
				.filter(meter -> meter.getId().getConventionTags(NamingConvention.camelCase)
						.stream()
						.anyMatch(tag -> tag.getKey().equals("status")))
				.flatMap(meter -> StreamSupport.stream(meter.measure().spliterator(), false))
				.filter(measurement -> measurement.getStatistic() == Statistic.Count)
				.map(Measurement::getValue)
				.mapToLong(Double::longValue)
				.sum();
	}
}