package com.freestrokes.constants;

public class PathConstants {

	/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Prefix
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String API = "/api";
    public static final String V1_API = API + "/v1";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Users
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String USERS = API + "/users";
    public static final String USERS_LOGIN = USERS + "/login";
    public static final String USERS_ME = USERS + "/me";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Products
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String PRODUCTS = API + "/products";
    public static final String PRODUCTS_DETAIL = PRODUCTS + "/{productId}";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Orders
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String ORDERS = API + "/orders";
    public static final String ORDERS_DETAIL = ORDERS + "/{orderId}";
    public static final String ORDERS_REVIEW = ORDERS_DETAIL + "/review";
    public static final String ORDERS_ACCEPT = ORDERS_DETAIL + "/accept";
    public static final String ORDERS_SHIPPING = ORDERS_DETAIL + "/shipping";
    public static final String ORDERS_COMPLETE = ORDERS_DETAIL + "/complete";
    public static final String ORDERS_REJECT = ORDERS_DETAIL + "/reject";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Common
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String HEALTH_CHECK = V1_API + "/health-check";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Auth
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String AUTH = V1_API + "/auth";
    public static final String LOGIN = AUTH + "/login";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Board
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String BOARDS = V1_API + "/boards";
    public static final String BOARD = BOARDS + "/{boardId}";

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	| Board Comment
	|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public static final String BOARD_COMMENTS = V1_API + "/board-comments";
    public static final String BOARD_COMMENT = BOARD_COMMENTS + "/{boardCommentId}";

}
