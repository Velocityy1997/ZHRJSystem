package com.web.common.util.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 响应对象封装
 *
 * @param <T>
 */
public class RestResponse<T> {
	
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private T data;
    
    @JsonProperty("description")
    private String description;
    
    
    public RestResponse() {
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public RestResponse<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public RestResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public RestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

	public String getDescription() {
		return this.description;
	}

	public RestResponse<T> setDescription(String description) {
		this.description = description;
        return this;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	
    
}
