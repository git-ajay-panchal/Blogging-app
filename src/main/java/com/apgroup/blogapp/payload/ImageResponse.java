package com.apgroup.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageResponse {
    private String imageName;

    public ImageResponse(String imageName) {
        this.imageName = imageName;
    }
}
