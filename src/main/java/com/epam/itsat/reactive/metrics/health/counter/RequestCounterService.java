package com.epam.itsat.reactive.metrics.health.counter;

public interface RequestCounterService {

	long getFailedRequestCount();

	long getTotalRequestCount();
}
