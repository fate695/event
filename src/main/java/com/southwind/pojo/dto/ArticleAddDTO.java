package com.southwind.pojo.dto;

import com.southwind.validation.StateValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ArticleAddDTO {
    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    @Pattern(regexp = "^\\S{1,10}$", message = "文章标题必须是1~10位非空字符")
    private String title;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    @Size(max = 10000, message = "文章内容不能超出10000")
    private String content;

    /**
     * 文章封面
     */
    @NotBlank(message = "文章封面不能为空")
    @URL(message = "文章封面格式不正确")
    private String coverImg;

    /**
     * 文章状态: 只能是[已发布] 或者 [草稿]
     */
    @NotBlank(message = "文章状态不能为空")
    @StateValid
    private String state;

    /**
     * 文章分类ID
     */
    @NotNull(message = "文章分类不能为空")
    private Integer categoryId;

}
