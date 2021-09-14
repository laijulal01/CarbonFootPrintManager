package com.allianz.coreader.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResult {
    private List<FieldValidationError> result=new ArrayList<>();

    public List<FieldValidationError> getResult() {
        return result;
    }

    public void setResult(List<FieldValidationError> result) {
        this.result = result;
    }
}
