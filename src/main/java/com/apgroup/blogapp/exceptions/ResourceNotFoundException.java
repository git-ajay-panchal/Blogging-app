package com.apgroup.blogapp.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue){
        super(String.format("%s not found with %s : %s ",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}

//As we know well that default constructor is provided by compiler
//automatically if there is no constructor. But, it also adds super() as the first statement.