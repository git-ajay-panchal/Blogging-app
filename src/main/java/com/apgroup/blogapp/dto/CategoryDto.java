package com.apgroup.blogapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryDto {

    private int catId;

    @NotEmpty
    private String catTitle;
    private String catDesc;
}
