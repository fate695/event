package com.southwind.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryUpdateDTO {
    @NotNull(message = "分类id不能为空")
    private Integer id;

    @NotBlank(message = "分类名称不能为空")
    @Size(max = 10, message = "分类名称不能超出10")
    private String categoryName;

    @NotBlank(message = "分类别名不能为空")
    @Size(max = 10, message = "分类别名不能超出10")
    private String categoryAlias;
}
