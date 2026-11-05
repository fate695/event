package com.southwind.controller;

import com.southwind.pojo.Category;
import com.southwind.pojo.Result;
import com.southwind.pojo.dto.CategoryAddDTO;
import com.southwind.pojo.dto.CategoryUpdateDTO;
import com.southwind.pojo.vo.CategoryDetailVO;
import com.southwind.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //新增文章分类
    @PostMapping()
    public Result add(@Valid @RequestBody CategoryAddDTO dto) {
        if (categoryService.add(dto.getCategoryName(), dto.getCategoryAlias())) {
            return Result.success();
        }
        return Result.error("新增失败");
    }

    //文章分类列表
    @GetMapping()
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    //获取文章分类详情
    @GetMapping("detail")
    public Result<CategoryDetailVO> detail(Integer id) {
        CategoryDetailVO detail = categoryService.getDetail(id);
        if (detail == null) {
            return Result.error("分类不存在");
        }
        return Result.success(detail);
    }

    //更新文章分类信息
    @PutMapping()
    public Result update(@Valid @RequestBody CategoryUpdateDTO dto) {
        if (categoryService.update(dto.getId(), dto.getCategoryName(), dto.getCategoryAlias())) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    //删除文章分类信息
    @DeleteMapping()
    public Result delete(@RequestParam @NotNull(message = "分类id不能为空") Integer id) {
        if (categoryService.delete(id)) {
            return Result.success();
        }
        return Result.error("删除失败");
    }
}
