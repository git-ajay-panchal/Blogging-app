package com.apgroup.blogapp.payload;

import com.apgroup.blogapp.dto.PostDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageSize;
    private int pageNum;
    private int totalPages;
    private boolean lastPage;
    private long totalElements;

}
