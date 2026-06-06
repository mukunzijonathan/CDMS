package com.example.CarDealership.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standardized API Error Response wrapper.
 * 
 * All error responses from the API follow this structure to provide:
 * - Timestamp of when the error occurred
 * - HTTP Status code
 * - Error message
 * - Detailed list of field errors (for validation)
 * - Error path (the endpoint that caused the error)
 */
@Schema(description = "Standardized API Error Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    @Schema(description = "Timestamp when error occurred", example = "2024-01-15T10:30:45.123456")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP Status Code", example = "404")
    private int status;

    @Schema(description = "Short error message", example = "Resource Not Found")
    private String error;

    @Schema(description = "Detailed error message", example = "Customer not found with id: '12345'")
    private String message;

    @Schema(description = "API endpoint path that caused the error", example = "/api/customers/12345")
    private String path;

    @Schema(description = "List of field-level validation errors")
    private List<FieldError> fieldErrors;

    // Constructors
    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(int status, String error, String message) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ApiErrorResponse(int status, String error, String message, String path) {
        this(status, error, message);
        this.path = path;
    }

    public ApiErrorResponse(int status, String error, String message, String path, List<FieldError> fieldErrors) {
        this(status, error, message, path);
        this.fieldErrors = fieldErrors;
    }

    // Getters & Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public List<FieldError> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(List<FieldError> fieldErrors) { this.fieldErrors = fieldErrors; }

    /**
     * Inner class for field-level validation errors.
     */
    @Schema(description = "Field-level validation error")
    public static class FieldError {
        @Schema(description = "Name of the field that failed validation", example = "email")
        private String field;

        @Schema(description = "The rejected value", example = "invalid-email")
        private Object rejectedValue;

        @Schema(description = "Validation error message", example = "Email should be valid")
        private String message;

        public FieldError(String field, Object rejectedValue, String message) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        // Getters & Setters
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public Object getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
