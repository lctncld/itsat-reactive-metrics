package com.epam.itsat.reactive.metrics.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class IndexHtmlFilter implements WebFilter {
	// FIXME: https://github.com/spring-projects/spring-boot/issues/9785

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		if (exchange.getRequest().getURI().getPath().equals("/")) {
			return chain.filter(exchange.mutate()
					.request(exchange.getRequest().mutate().path("/index.html").build())
					.build());
		}
		return chain.filter(exchange);
	}
}