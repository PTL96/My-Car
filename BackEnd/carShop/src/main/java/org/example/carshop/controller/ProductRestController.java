package org.example.carshop.controller;


import org.example.carshop.dto.History;
import org.example.carshop.dto.ProductDto;
import org.example.carshop.dto.ProductView;
import org.example.carshop.entity.Category;
import org.example.carshop.entity.Product;
import org.example.carshop.service.impl.CategoryService;
import org.example.carshop.service.impl.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("product")
@CrossOrigin("*")
public class ProductRestController {
@Autowired
private ProductService productService;
@Autowired
CategoryService categoryService;

@GetMapping("")
public ResponseEntity<Page<ProductView>>getAll(@PageableDefault(page = 0, size = 6)Pageable pageable,
                                               @RequestParam(required = false, defaultValue = "") String name,
                                               @RequestParam(required = false, defaultValue = "") String category){
Page<ProductView>productPage = productService.getAllProduct(name, category, pageable);
if (productPage.isEmpty()){
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
return new ResponseEntity<>(productPage, HttpStatus.OK);
}

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.hasErrors(), HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Category category = new Category();
        category.setCategoryId(productDto.getCategory().getCategoryId());
        product.setCategory(category);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product product = productService.findByIdProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        Product product = productService.findByIdProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<History>>getAllhst(@PageableDefault(page = 0, size = 5)Pageable pageable,
                                               @RequestParam(required = false, defaultValue = "") String name,
                                               @RequestParam(required = false, defaultValue = "") String category){
        Page<History>historyPage = productService.getAllHistory(name, category, pageable);
        if (historyPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(historyPage, HttpStatus.OK);
    }

}