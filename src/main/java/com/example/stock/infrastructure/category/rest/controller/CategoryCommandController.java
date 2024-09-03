package com.example.stock.infrastructure.category.rest.controller;


import com.example.stock.application.category.command.CategoryCreateHandler;
import com.example.stock.application.category.command.CategoryDeleteHandler;
import com.example.stock.application.category.command.CategoryUpdateHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.dto.command.CategoryEditCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@AllArgsConstructor
@Tag(name ="Category Command Controller")
public class CategoryCommandController {
    private final CategoryCreateHandler categoryCreateHandler;
    private final CategoryUpdateHandler categoryUpdateHandler;
    private  final CategoryDeleteHandler categoryDeleteHandler;


    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("/")
    public CategoryDto create(@RequestBody CategoryCreateCommand createCommand){
        return categoryCreateHandler.execute(createCommand);
    }
    @Operation(summary = "Update an existing Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @PutMapping("/{id}")
    public CategoryDto edit(@RequestBody CategoryEditCommand categoryEditCommand, @PathVariable Long id){
        return categoryUpdateHandler.execute(categoryEditCommand, id);
    }

    @Operation(summary = "Delete a category by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        categoryDeleteHandler.execute(id);
    }
}
