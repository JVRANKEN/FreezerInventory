package com.freezer.inventory.utility;

public class UrlMapping {
    // http://localhost:8080/swagger-ui/index.html
    // CRUD BASIC
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String ID = "/id";
    public static final String GET = "/get";
    public static final String SEARCH = "/search";
    //
    public static final String FREEZER = "/freezer";
    public static final String FREEZER_INTERNAL_TESTING = FREEZER + "/internaltesting";

    public static final String GET_ITEM = GET + "/item";
    public static final String GET_TYPE = GET + "/type";
    public static final String GET_CATEGORY = GET + "/category";
    public static final String GET_EXPIRYDATE = GET + "/expirydate";
    public static final String GET_FROZENDATE_ADD_MONTHS = GET + "/frozendate";
    public static final String DELETE_BY_DOCUMENT_ID = DELETE + "/documentid";
    public static final String DELETE_BY_ITEM = DELETE + "/item";


}
