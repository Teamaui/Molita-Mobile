package com.molita.molita.model.data;

import java.util.List;

public class ResponseListData<T> {
    private String code;
    private boolean success;
    private String message;
    private List<T> data;

    // Getter dan Setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
