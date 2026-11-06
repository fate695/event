package com.southwind.pojo.dto;

import com.southwind.validation.StateValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ArticleUpdateDTO {
    @NotNull(message = "文章ID不能为空")
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$", message = "文章标题必须是1~10位非空字符")
    @NotBlank(message = "文章标题不能为空")
    private String title;
    @Size(max = 10000, message = "文章内容不能超出10000")
    @NotBlank(message = "文章内容不能为空")
    private String content;
    @NotBlank(message = "文章封面不能为空")
    @URL(message = "文章封面格式不正确")
    private String coverImg;
    @NotBlank(message = "文章状态不能为空")
    @StateValid
    private String state;
    @NotNull(message = "文章分类不能为空")
    private Integer categoryId;
}
