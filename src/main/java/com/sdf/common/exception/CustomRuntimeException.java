package com.sdf.common.exception;

import org.springframework.stereotype.Component;

/**
 * 自定义异常
 *
 * @author Jonny
 * @version 1.0.0
 * @date 2016-04-15
 * @time 21:58
 */
@Component
public class CustomRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(Long ibillid, Long idoctypeid,
                                  String returnMsg, CustomRuntimeException e) {
        //super(ibillid, idoctypeid);
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRuntimeException(long ibillid, long doctypeid, String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRuntimeException(long doctypeid, String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(Throwable cause) {
        super(cause);
    }
}