package com.example.stock.domain.static_variables;

public class StaticData {
    public static final String NAME_MANDATORY = "Name is mandatory";
    public static final String NAME_MAX_ERROR ="Name don't be bigger than 50 characters";
    public static final int MAXIMUM_ALLOW_LETTERS = 50;
    public static final int MAXIMUM_ALLOW_LETTERS_DESCRIPTION = 90;
    public static final String DESCRIPTION_MESSAGE = "Description is mandatory";
    public static final String DESCRIPTION_MESSAGE_MAX_ERROR = "Description don't be bigger than 90 characters";
    public static final int MAXIMUM_ALLOW_LETTERS_VAR = 120;
    public static final String DESCRIPTION_MESSAGE_MAX_ERROR_VAR= "Description don't be bigger than 120 characters";

    public static final String PRICE_MANDATORY = "Price is mandatory";
    public static final String PRICE_MIN_ZERO = "Price must be greater than zero.";
    public static final String QUANTITY_MANDATORY = "quantity is mandatory";
    public static final String QUANTITY_MESSAGE_MIN_ERROR = "Quantity must be greater than zero";
    public static final int ZERO_CONSTANT = 0;
    public static final int TEN_CONSTANT = 10;
    public static final int THREE_CONSTANT = 3;

    public static final String MESSAGE_ERROR_ADD = "Article Exist";
    public static final String MESSAGE_ERROR_CATEGORY = "Category height Invalid";
    public static final String MESSAGE_ERROR_CATEGORY_NULL = "Category Array Null";
    public static final String MESSAGE_ERROR_CATEGORY_DUPLICATED = "Category duplicated: ";
    public static final String MESSAGE_ERROR_CATEGORY_FOUND= "Category duplicated: ";
    public static final String MESSAGE_ERROR_ADD_CATEGORY = "Category Exist";
    public static final String MESSAGE_ERROR_BRAND = "Brand Not Found";
    public static final String MESSAGE_ERROR_BRAND_NOT = "Brand Inject Not Found";
    public static final String MESSAGE_ERROR_ADD_BRAND = "Brand Exist";

    public static final String MESSAGE_PAGE_VALID = "Page index must be non-negative and size must be greater than zero.";


    class StringUtils {
        private StringUtils() {
            throw new IllegalStateException("Utility class");
        }
    }

}

