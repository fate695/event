package com.southwind.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryAddDTO {
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    @Size( max = 10, message = "分类名称不能超出10")
    private String categoryName;

    /**
     * 分类别名
     */
    @NotBlank(message = "分类别名不能为空")
    @Size(max = 10, message = "分类别名不能超出10")
    private String categoryAlias;
}
