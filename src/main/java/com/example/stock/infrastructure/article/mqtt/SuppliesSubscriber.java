package com.example.stock.infrastructure.article.mqtt;


import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.stock.domain.static_variables.StaticData.QUEUE_NAME;


@Component
@AllArgsConstructor
public class SuppliesSubscriber {
    ArticleSuppliesHandler articleSuppliesHandler;
    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage( String messageSupply){
            articleSuppliesHandler.execute(messageSupply);
    }
}
