package com.csk.gateway.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.servlet.ExecutionStrategyProvider;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphqlConfig {

	@Bean
	public GraphQLSchema schema(List<GraphQLResolver<?>> resolvers,

			@Value("classpath*:/graphql/*.graphqls") Resource[] schemas, SchemaParserOptions optons) {
		String schemasString = Arrays.stream(schemas).map(this::toStringUnchecked).reduce((s1, s2) -> s1 + '\n' + s2)
				.orElse("");

		return SchemaParser.newParser().schemaString(schemasString).resolvers(resolvers).options(optons).build()
				.makeExecutableSchema();

	}

	@Bean
	public SchemaParserOptions schemaParserOptions() {
		return SchemaParserOptions.newOptions().objectMapperConfigurer((mapper, context) -> {
			mapper.setAnnotationIntrospector(NopAnnotationIntrospector.instance)
					.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		}).build();
	}

	@Bean
	@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public GraphQL graphQl(GraphQLSchema schema, ExecutionStrategyProvider provider) {
		return GraphQL.newGraphQL(schema)
.queryExecutionStrategy(provider.getQueryExecutionStrategy())
.subscriptionExecutionStrategy(provider.getSubscriptionExecutionStrategy())
.mutationExecutionStrategy(provider.getMutationExecutionStrategy())
.build();
	}

	private String toStringUnchecked(Resource resource) {
		try (InputStream is = resource.getInputStream()) {
			return IOUtils.toString(is, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			throw new RuntimeException("Unable to read Schema", e);
		}

	}

}
