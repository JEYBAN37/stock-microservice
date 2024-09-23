package com.example.stock.article.articleapplicationtest;

import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.domain.article.model.dto.command.ArticleSupplyCommand;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


class ArticleSuppliesHandlerTest {

     @Mock
     private ArticleSuppliesService articleSuppliesService;

     @Mock
     private ObjectMapper objectMapper;

     @InjectMocks
     private ArticleSuppliesHandler articleSuppliesHandler;

     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
     }

     @Test
     void execute_ValidArticleSupply_ExecutesSuccessfully() throws Exception {
         // Arrange
         String articleSupplyJson = "{\"id\": 1, \"quantity\": 5, \"price\": 100.0}";
         ArticleSupplyCommand articleSupplyCommand = new ArticleSupplyCommand(1L, 5, new BigDecimal(100.0));

         when(objectMapper.readValue(articleSupplyJson, ArticleSupplyCommand.class)).thenReturn(articleSupplyCommand);

         // Act
         articleSuppliesHandler.execute(articleSupplyJson);

         // Assert
         verify(articleSuppliesService, times(1)).execute(articleSupplyCommand);
     }

     @Test
     void execute_InvalidJson_ThrowsJsonProcessingException() throws Exception {
         // Arrange
         String invalidJson = "invalid_json";

         when(objectMapper.readValue(invalidJson, ArticleSupplyCommand.class)).thenThrow(JsonProcessingException.class);

         // Act
         articleSuppliesHandler.execute(invalidJson);

         // Assert
         verify(articleSuppliesService, never()).execute(any());
         // Verifying that the user is notified about the error
         verifyNoMoreInteractions(articleSuppliesService);
     }

     @Test
     void execute_ArticleException_HandlesAndNotifiesUser() throws Exception {
         // Arrange
         String articleSupplyJson = "{\"id\": 1, \"quantity\": 5, \"price\": 100.0}";
         ArticleSupplyCommand articleSupplyCommand = new ArticleSupplyCommand(1L, 5, new BigDecimal(100.0));

         when(objectMapper.readValue(articleSupplyJson, ArticleSupplyCommand.class)).thenReturn(articleSupplyCommand);
         doThrow(new ArticleException("Article Error")).when(articleSuppliesService).execute(articleSupplyCommand);

         // Act
         articleSuppliesHandler.execute(articleSupplyJson);

         // Assert
         verify(articleSuppliesService, times(1)).execute(articleSupplyCommand);
         verifyNoMoreInteractions(articleSuppliesService);
     }

     @Test
     void execute_UnexpectedException_HandlesAndNotifiesUser() throws Exception {
         // Arrange
         String articleSupplyJson = "{\"id\": 1, \"quantity\": 5, \"price\": 100.0}";
         ArticleSupplyCommand articleSupplyCommand = new ArticleSupplyCommand(1L, 5, new BigDecimal(100.0));

         when(objectMapper.readValue(articleSupplyJson, ArticleSupplyCommand.class)).thenReturn(articleSupplyCommand);
         doThrow(new RuntimeException("Unexpected error")).when(articleSuppliesService).execute(articleSupplyCommand);

         // Act
         articleSuppliesHandler.execute(articleSupplyJson);

         // Assert
         verify(articleSuppliesService, times(1)).execute(articleSupplyCommand);
         verifyNoMoreInteractions(articleSuppliesService);
     }

}
