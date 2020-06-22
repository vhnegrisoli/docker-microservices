package br.com.dge.produtoapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app-config.topic.produto}")
    private String produtonTopic;
    @Value("${app-config.queue.produto-atualizar-estoque}")
    private String produtoAtualizarEstoqueMq;
    @Value("${app-config.key.produto-atualizar-estoque}")
    private String produtoAtualizarEstoqueKey;

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(produtonTopic);
    }

    @Bean
    Queue produtoAtualizarEstoqueMq() {
        return new Queue(produtoAtualizarEstoqueMq, true);
    }

    @Bean
    public Binding produtoAtualizarEstoqueBinding(TopicExchange exchange) {
        return BindingBuilder.bind(produtoAtualizarEstoqueMq()).to(exchange).with(produtoAtualizarEstoqueKey);
    }
}