package com.southwind.controller;

import com.southwind.pojo.Article;
import com.southwind.pojo.PageBean;
import com.southwind.pojo.Result;
import com.southwind.pojo.dto.ArticleAddDTO;
import com.southwind.pojo.dto.ArticleUpdateDTO;
import com.southwind.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    /**
     * 新增文章*/
    @PostMapping()
    public Result add(@RequestBody @Valid ArticleAddDTO dto) {
        if(articleService.add(dto)){
            return Result.success();
        }
        return Result.error("新增失败");
    }

    /**
     * 文章列表（条件分页）
     *
     * @param pageNum    当前页码（必填）
     * @param pageSize   每页条数（必填）
     * @param categoryId 文章分类ID（可选）
     * @param state      发布状态（可选：已发布|草稿）
     */
    @GetMapping()
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false)  Integer categoryId,
                                          @RequestParam(required = false)  String state)
    {
        return Result.success(articleService.list(pageNum, pageSize, categoryId, state));
    }

    /**
     * 获取文章详情
     * @param id 文章ID
     * */
    @GetMapping("detail")
    public Result<Article> detail(@NotNull(message = "文章ID不能为空") Integer id) {
     return Result.success(articleService.getById(id));
    }

    /**
     * 更新文章信息*/
    @PutMapping()
    public Result update(@RequestBody @Valid ArticleUpdateDTO dto) {
        if(articleService.update(dto)){
            return Result.success();
        }
        return Result.error("更新失败");
    }

    /**
     * 删除文章*/
    @DeleteMapping()
    public Result delete(@NotNull(message = "文章ID不能为空") Integer id) {
        if(articleService.removeById(id)){
            return Result.success();
        }
        return Result.error("删除失败");
    }
}
