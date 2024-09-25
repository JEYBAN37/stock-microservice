package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleCreateService;
import com.example.stock.domain.article.service.ArticleFilterService;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.category.service.CategoryListArticle;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.stock.domain.static_variables.StaticData.EXCHANGE_NAME;
import static com.example.stock.domain.static_variables.StaticData.QUEUE_NAME;

@Configuration
public class ArticleBean{


    @Bean
    public ArticleCreateService articleCreateService (ArticleRepository articleRepository, ArticleDao articleDao, CategoryListArticle categoryListArticle,
                                                      BrandDao brandDao)
    {
        return new ArticleCreateService(articleRepository,articleDao,categoryListArticle,brandDao);
    }
   @Bean
    public ArticleFilterService articleFilterService (ArticleDtoMapper articleDtoMapper,ArticleDao articleDao){
        return new ArticleFilterService(articleDtoMapper,articleDao);
    }
    @Bean
    public ArticleSuppliesService articleSuppliesService (ArticleRepository articleRepository, ArticleDao articleDao){
        return new ArticleSuppliesService(articleDao,articleRepository);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.key");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
