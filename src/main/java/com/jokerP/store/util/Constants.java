package com.jokerP.store.util;

public class Constants {
    private Constants() {}

    public static class Message {
        private Message() {}

        public static final String USER_NOT_FOUND = "User not found!";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
        public static final String PASSWORD_DOES_NOT_MATCH = "Password doesn't match!";

        public static final String CART_NOT_FOUND = "Cart not found!";
        public static final String CART_EMPTY = "Cart is empty!";

        public static final String PRODUCT_NOT_FOUND_IN_CART = "Product not found in Cart!";
        public static final String PRODUCT_NOT_FOUND = "Product not found !";

        public static final String CATEGORY_NOT_FOUND = "Category not found!";

        public static final String ACCESS_DENIED = "Access denied!";

        public static final String ORDER_NOT_FOUND = "Order not found!";

        public static final String INVALID_REQUEST_BODY = "Invalid request body";

        public static final String ERROR_CREATE_CHECKOUT_SESSION = "Error creating checkout session";
        public static final String STRIPE_INVALID_SIGNATURE = "Invalid signature";
        public static final String ERROR_DESERIALIZE_STRIPE = "Cound not deserialize stripe event. Check the SDK and API version!";
    }
}
