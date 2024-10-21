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


    public static final String REGISTER = "/register";
    public static final String LOGIN = "/dologin";
    public static final String GETPROFILE = "/getprofile";
    public static final String LIST_MY_PRODUCTS = "/list-my-products";
    public static final String ADD_PRODUCT = "/add-product";
}