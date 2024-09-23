package com.example.stock.infrastructure.article.mqtt;


import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.infrastructure.beanconfiguration.ArticleBean;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SuppliesSubscriber {
    ArticleSuppliesHandler articleSuppliesHandler;
    @RabbitListener(queues = ArticleBean.QUEUE_NAME)
    public void receiveMessage( String messageSupply){
            articleSuppliesHandler.execute(messageSupply);
    }
}
