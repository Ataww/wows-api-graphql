package eu.ncouret.wows.graphql

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@SpringBootApplication
class Application {

    @Bean
    fun webClient(@Value("\${application.wargaming.host}") host: String): WebClient =
        WebClient.builder().baseUrl(host).build()

    @Bean
    fun jacksonBuilder(): Jackson2ObjectMapperBuilder {
        val builder = Jackson2ObjectMapperBuilder()
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        builder.failOnUnknownProperties(false)
        builder.failOnEmptyBeans(false)
        return builder
    }

    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        return builder.build()
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}