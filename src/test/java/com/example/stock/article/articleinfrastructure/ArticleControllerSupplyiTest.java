package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.command.ArticleCreateHandler;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.infrastructure.article.rest.controller.ArticleCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerSupplyiTest {

 @Autowired
 private MockMvc mockMvc;

 @Mock
 private ArticleCreateHandler articleCreateHandler;

 @InjectMocks
 private ArticleCommandController articleCommandController;

 @BeforeEach
 public void setUp() {
  MockitoAnnotations.openMocks(this);
 }

 @Test
 @WithMockUser // Simula un usuario autenticado
 public void testCreateArticle_WithAuth() throws Exception {
  // Arrange
  ArticleCreateCommand createCommand = new ArticleCreateCommand();
  // Set necessary fields for createCommand

  ArticleDto expectedArticleDto = new ArticleDto();
  // Set necessary fields for expectedArticleDto

  when(articleCreateHandler.execute(any(ArticleCreateCommand.class))).thenReturn(expectedArticleDto);

  // Act & Assert
  mockMvc.perform(post("/admin/articles/")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{   \"name\":\"ARMARIOS PLASATICOs\",\n" +
                          "    \"description\":\"Laptop de 15 pulgadas, procesador Intel i7\",\n" +
                          "    \"quantity\":80,\n" +
                          "    \"price\":50.54,\n" +
                          "    \"brand\":10,\n" +
                          "    \"articleCategories\":[3,8,6,4] }"))
          .andExpect(status().isUnauthorized());
 }

 @Test
 @WithMockUser // Simula un usuario autenticado
 public void testCreateArticle_Conflict() throws Exception {
  // Arrange
  ArticleCreateCommand createCommand = new ArticleCreateCommand();
  // Set necessary fields for createCommand

  when(articleCreateHandler.execute(any(ArticleCreateCommand.class))).thenThrow(new RuntimeException("Conflict"));

  // Act & Assert
  mockMvc.perform(post("/admin/articles/")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{    \"name\":\"ARMARIOS PLASATICOs\",\n" +
                          "    \"description\":\"Laptop de 15 pulgadas, procesador Intel i7\",\n" +
                          "    \"quantity\":80,\n" +
                          "    \"price\":50.54,\n" +
                          "    \"brand\":10,\n" +
                          "    \"articleCategories\":[3,8,6,4] }"))
          .andExpect(status().isUnauthorized());
 }
}
