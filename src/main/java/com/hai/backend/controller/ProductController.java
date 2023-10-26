package com.hai.backend.controller;

import com.hai.backend.entity.ImageEntity;
import com.hai.backend.entity.ProductEntity;
import com.hai.backend.entity.ReviewEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.modal.Review;
import com.hai.backend.service.ImageService;
import com.hai.backend.service.ProductService;
import com.hai.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;

    @Autowired
    ImageService imageService;

    @GetMapping("")
    public ApiReponse<List<ProductEntity>> getAll() {
        try {
            return new ApiReponse<>(true, productService.getAll());
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/category/{id}")
    public ApiReponse<List<ProductEntity>> getAllByCategoryId(@PathVariable int id) {
        try {
            List<ProductEntity> list = productService.getAllByCategory(id);
            return new ApiReponse<>(true, list);
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }


    @GetMapping("/search")
    public ApiReponse<List<ProductEntity>> search(@RequestParam(defaultValue = "") String query) {
        try {
            return new ApiReponse<>(true, productService.search(query));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    //get by sold

    @PostMapping("/add")
    public ApiReponse<ProductEntity> saveProducrt(@RequestBody ProductEntity productEntity) {
        try {
            List<ImageEntity> images = productEntity.getImages();

            for (ImageEntity image : images) {
                if (!imageService.check(image.getId())) {
                    imageService.saveImage(image);
                }
            }
            ProductEntity product = productService.saveProduct(productEntity);
            return new ApiReponse<>(true, product);
        } catch (Exception exception) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/{id}")
    public ApiReponse<ProductEntity> getProductById(@PathVariable int id) {
        try {
            ProductEntity product = productService.getProductById(id);
            List<Review> reviews = reviewService.getAllReviewByProductId(id);

            double total = 0;
            for (Review review:reviews) {
                total += review.getRate();
            }
            double avg = total / reviews.size();
            product.setRate(avg);


            return new ApiReponse<>(true, product);
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiReponse<ProductEntity> deleteProduct(@PathVariable int id) {
        if (productService.check(id)) {
            ProductEntity product = productService.getProductById(id);
            product.setDelete(true);
            productService.saveProduct(product);

            return new ApiReponse<>(true, product);

        } else {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/update")
    public ApiReponse<ProductEntity> updateProduct(@RequestBody ProductEntity productEntity) {
        try {
            if (productService.check(productEntity.getId())) {
                List<ImageEntity> images = productEntity.getImages();

                for (ImageEntity image : images) {
                    if (!imageService.check(image.getId())) {
                        imageService.saveImage(image);
                    }
                }

                if(Double.isNaN(productEntity.getRate())){
                    productEntity.setRate(0);
                }

                ProductEntity product = productService.saveProduct(productEntity);
                return new ApiReponse<>(true, product);
            } else {
                return new ApiReponse<>(false, null);
            }
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/status/{id}")
    public ApiReponse<ProductEntity> updateStatusProduct(@PathVariable int id) {
        if (productService.check(id)) {
            ProductEntity product = productService.getProductById(id);
            product.setStatus(!product.isStatus());
            productService.saveProduct(product);

            return new ApiReponse<>(true, product);
        } else {
            return new ApiReponse<>(false, null);
        }
    }
}
