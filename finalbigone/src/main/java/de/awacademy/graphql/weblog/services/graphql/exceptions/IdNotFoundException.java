package de.awacademy.graphql.weblog.services.graphql.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class IdNotFoundException extends RuntimeException implements GraphQLError {


    private final String id;

    public IdNotFoundException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Dokument mit der ID " + id + " konnte nicht gefunden werden!";
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
    
}
