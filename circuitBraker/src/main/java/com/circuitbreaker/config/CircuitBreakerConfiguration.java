package com.circuitbreaker.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.core.IntervalFunction;

@Configuration
public class CircuitBreakerConfiguration {

	@Bean
	public CircuitBreaker circuitBreaker() {
		return CircuitBreaker.of("mi-primer-cb", this.buildConfig3());

	}

	CircuitBreakerConfig buildConfig1() {
		return CircuitBreakerConfig.custom().slidingWindowType(SlidingWindowType.COUNT_BASED).slidingWindowSize(4) // default
																													// 100
				.failureRateThreshold(50f) // default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitDurationInOpenState(Duration.ofSeconds(5)) // default 60s (fixed)
				.writableStackTraceEnabled(false).build();// simplifica la info de errores
	}
	/*
	 *
- `slidingWindowType(SlidingWindowType.COUNT_BASED)`: Define el tipo de ventana deslizante como basada en recuento. Esto significa que se controlará el número de llamadas en lugar del tiempo transcurrido.

- `slidingWindowSize(4)`: Establece el tamaño de la ventana deslizante en 4. Esto significa que se considerarán las últimas 4 llamadas para evaluar el estado del Circuit Breaker.

- `failureRateThreshold(50f)`: Define el umbral de tasa de errores en un 50%. Cuando la tasa de errores supera este valor, el Circuit Breaker se abrirá.

- `permittedNumberOfCallsInHalfOpenState(2)`: Especifica el número permitido de llamadas en estado de medio abierto. En este caso, se permiten 2 llamadas antes de intentar cambiar al estado cerrado nuevamente.

- `waitDurationInOpenState(Duration.ofSeconds(5))`: Establece la duración de espera en estado abierto en 5 segundos. Durante este tiempo, el Circuit Breaker rechazará las llamadas.

- `writableStackTraceEnabled(false)`: Deshabilita la generación de trazas de pila para las excepciones.

	 */

	CircuitBreakerConfig buildConfig2() {
		return CircuitBreakerConfig.custom().slidingWindowType(SlidingWindowType.COUNT_BASED).slidingWindowSize(4) // default
																													// 100
				.failureRateThreshold(50f) // default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitIntervalFunctionInOpenState(
						IntervalFunction.ofExponentialBackoff(Duration.ofSeconds(2), 3, Duration.ofSeconds(100)))
				.automaticTransitionFromOpenToHalfOpenEnabled(true).writableStackTraceEnabled(false).build();
	}
	/*
	 * 
- `slidingWindowType(SlidingWindowType.COUNT_BASED).slidingWindowSize(4)`: Establece el tipo de ventana deslizante como "COUNT_BASED" y el tamaño de la ventana en 4, lo que significa que el Circuit Breaker contará las llamadas recientes para tomar decisiones.

- `failureRateThreshold(50f)`: Define el umbral de tasa de fallo en 50%. Si más del 50% de las llamadas fallan, el Circuit Breaker se abrirá.

- `permittedNumberOfCallsInHalfOpenState(2)`: Establece el número de llamadas permitidas en el estado medio abierto. En este caso, se permiten 2 llamadas antes de evaluar si el Circuit Breaker debe volver a abrirse o cerrarse.

- `waitIntervalFunctionInOpenState(IntervalFunction.ofExponentialBackoff(Duration.ofSeconds(2), 3, Duration.ofSeconds(100))`: Define la función de intervalo de espera en el estado abierto. En este caso, se utiliza una estrategia de retardo exponencial que aumenta el tiempo de espera en cada intento.

- `automaticTransitionFromOpenToHalfOpenEnabled(true)`: Habilita la transición automática del estado abierto al estado medio abierto en el Circuit Breaker. Esto permite que el Circuit Breaker cambie de estado automáticamente después de un tiempo determinado.

- `writableStackTraceEnabled(false)`: Deshabilita la generación de trazas de pila detalladas para las excepciones en el Circuit Breaker, lo que simplifica los mensajes de error sin incluir información detallada de las llamadas de funciones.


	 */

	CircuitBreakerConfig buildConfig3() {

		return CircuitBreakerConfig.custom().slidingWindowType(SlidingWindowType.COUNT_BASED).slidingWindowSize(4) // default
																													// 100
				.failureRateThreshold(50f) // default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitDurationInOpenState(Duration.ofSeconds(5)) // default 60s (fixed)
				.slowCallRateThreshold(20f) // 20% of calls are slow
				.slowCallDurationThreshold(Duration.ofSeconds(2)) // > 2s is slow
				.writableStackTraceEnabled(false).build();
		/*
		
- `slidingWindowType(SlidingWindowType.COUNT_BASED).slidingWindowSize(4)`: Se establece el tipo de ventana deslizante como "COUNT_BASED" y el tamaño de la ventana en 4. Esto significa que el Circuit Breaker contará las llamadas recientes para decidir si debe abrirse o cerrarse.

- `failureRateThreshold(50f)`: Define el umbral de tasa de falla en 50%. Esto indica que si más del 50% de las llamadas resultan en error, el Circuit Breaker se abrirá.

- `permittedNumberOfCallsInHalfOpenState(2)`: Establece el número de llamadas permitidas en el estado medio abierto. En este caso, se permiten 2 llamadas antes de evaluar si el Circuit Breaker debe volver a abrirse o cerrarse.

- `waitDurationInOpenState(Duration.ofSeconds(5))`: Define la duración de espera en el estado abierto antes de pasar al estado medio abierto. Aquí se ha configurado en 5 segundos.

- `slowCallRateThreshold(20f)`: Indica que el 20% de las llamadas lentas se considerarán como lentas. Esto puede ser útil para monitorear y manejar llamadas que están tardando más de lo normal.

- `slowCallDurationThreshold(Duration.ofSeconds(2))`: Establece la duración a partir de la cual se considera que una llamada es lenta (más de 2 segundos en este caso).

- `writableStackTraceEnabled(false)`: Al igual que en los ejemplos anteriores, deshabilita la generación de trazas de pila detalladas para las excepciones en el Circuit Breaker.

		 */
	}

	CircuitBreakerConfig buildConfig4() {

		return CircuitBreakerConfig.custom().slidingWindowType(SlidingWindowType.TIME_BASED).slidingWindowSize(4) // default
																													// 100
				.failureRateThreshold(50f) // default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitDurationInOpenState(Duration.ofSeconds(2)) // default 60s (fixed)
				.writableStackTraceEnabled(false).automaticTransitionFromOpenToHalfOpenEnabled(true)
				.minimumNumberOfCalls(4).build();
	}
/*

- `slidingWindowType(SlidingWindowType.TIME_BASED).slidingWindowSize(4)`: Aquí se establece el tipo de ventana deslizante como "TIME_BASED" y el tamaño de la ventana en 4. Esto significa que el Circuit Breaker contará las llamadas recientes en función del tiempo para tomar decisiones.

- `failureRateThreshold(50f)`: Define el umbral de tasa de fallo en 50%. Si más del 50% de las llamadas resultan en error, el Circuit Breaker se abrirá.

- `permittedNumberOfCallsInHalfOpenState(2)`: Establece el número de llamadas permitidas en el estado medio abierto. En este caso, se permiten 2 llamadas antes de evaluar si el Circuit Breaker debe volver a abrirse o cerrarse.

- `waitDurationInOpenState(Duration.ofSeconds(2))`: Define la duración de espera en el estado abierto antes de pasar al estado medio abierto. Aquí se ha configurado en 2 segundos en lugar de los 60 segundos por defecto.

- `writableStackTraceEnabled(false)`: Deshabilita la generación de trazas de pila detalladas para las excepciones en el Circuit Breaker.

- `automaticTransitionFromOpenToHalfOpenEnabled(true)`: Habilita la transición automática del estado abierto al estado medio abierto en el Circuit Breaker.

- `minimumNumberOfCalls(4)`: Establece el número mínimo de llamadas antes de que el Circuit Breaker pueda abrirse. En este caso, se requieren al menos 4 llamadas antes de que se aplique la lógica del Circuit Breaker.


 */
}