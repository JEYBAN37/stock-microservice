package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.infrastructure.article.mqtt.SuppliesSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;

public class SuppliesSubscriberTest {

    @Mock
    private ArticleSuppliesHandler articleSuppliesHandler;

    @InjectMocks
    private SuppliesSubscriber suppliesSubscriber;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void receiveMessage_ShouldInvokeArticleSuppliesHandler() {
        // Arrange
        String messageSupply = "Sample supply message";

        // Act
        suppliesSubscriber.receiveMessage(messageSupply);

        // Assert
        verify(articleSuppliesHandler, times(1)).execute(messageSupply);
    }
}
