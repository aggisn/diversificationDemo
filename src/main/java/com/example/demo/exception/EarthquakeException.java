package com.example.demo.exception;

/**
 * @author 顾鹏飞
 * @date 2021/07/22
 * @description 自定义异常返回
 */

public class EarthquakeException extends Exception {

    private static final long serialVersionUID = 266495151581594848L;

    private int errorCode;

    private String errorMsg;

    public EarthquakeException() {
        super();
    }

    public EarthquakeException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public EarthquakeException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public EarthquakeException(int errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public EarthquakeException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * server error code, use http code 400 403 throw exception to user 500 502
     * 503 change ip and retry
     */
    /**
     *  invalid param
     */
    public static final int INVALID_PARAM = 400;
    /**
     *  no right
     */
    public static final int NO_RIGHT = 403;
    /**
     *  not found
     */
    public static final int NOT_FOUND = 404;

    /**
     *  conflict
     */
    public static final int CONFLICT = 409;
    /**
     *  server error
     */
    public static final int SERVER_ERROR = 500;
    /**
     *  bad gateway
     */
    public static final int BAD_GATEWAY = 502;
    /**
     *  over threshold
     */
    public static final int OVER_THRESHOLD = 503;
}
