package com.example.stock.domain.article.model.constant;
public class ArticleConstant {
    private ArticleConstant() {
        throw new IllegalStateException("Utility class");
    }
    public static final String TASK_NOT_FOUND_MESSAGE_ERROR = "No found Article with id %s";
    public static final String MESSAGE_ERROR_UPDATE = "Article No Exist";
    public static final String MESSAGE_ERROR_LIST_EMPTY = "Supplies Empty";
    public static final String MESSAGE_ERROR_QUANTITY = "Quantity invalid";


}
