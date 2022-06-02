package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.CategoryDto;
import uz.gullbozor.gullbozor.entity.Category;
import uz.gullbozor.gullbozor.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto category) {
        ApiResponse apiResponse = categoryService.addCategory(category);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@RequestBody CategoryDto category, @PathVariable Long id) {
        ApiResponse apiResponse = categoryService.editCategory(category, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        ApiResponse apiResponse = categoryService.getCategoryById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/allParentCategory")
    public ResponseEntity<List<Category>> getParentCategoryList() {
        List<Category> categoryList = categoryService.categoryParentList();
        return ResponseEntity.ok(categoryList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id) {
        ApiResponse apiResponse = categoryService.deleteCategoryById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
