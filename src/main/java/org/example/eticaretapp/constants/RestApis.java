package org.example.eticaretapp.constants;

public class RestApis {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION + DEVELOPER;

    public static final String USER = ROOT + "/user";
    public static final String AUTH = ROOT + "/auth";
    public static final String PRODUCT = ROOT + "/product";
    public static final String IMAGE = ROOT + "/image";
    public static final String SHOPPING_CART = ROOT + "/shopping-cart";
    public static final String CART_DETAILS = ROOT + "/cart-details";


    public static final String REGISTER = "/register";
    public static final String LOGIN = "/dologin";
    public static final String GETPROFILE = "/getprofile";
    public static final String LIST_MY_PRODUCTS = "/list-my-products";
    public static final String ADD_PRODUCT = "/add-product";
    public static final String DELETE_PRODUCT = "/delete-product";
    public static final String UPDATE_PRODUCT = "/update-product";
    public static final String ADDIMAGE = "/add-image";
    public static final String FINDALL = "/find-all";
    public static final String UPLOADPHOTO = "/upload-photo";
    public static final String VERIFYACCOUNT = "/verify-account";
    public static final String ADDPRODUCT_TO_CART = "/add-product-to-cart";
    public static final String LISTPRODUCTS_INCART = "/list-products-in-cart";
    public static final String FILTER = "/filter";
    public static final String LIST_OLD_CARTS = "/list-old-carts";
}