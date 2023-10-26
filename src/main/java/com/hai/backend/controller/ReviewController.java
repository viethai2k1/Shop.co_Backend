package com.hai.backend.controller;

import com.hai.backend.entity.OrderEntity;
import com.hai.backend.entity.ProductEntity;
import com.hai.backend.entity.ReviewEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.*;
import com.hai.backend.service.Format;
import com.hai.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("")
    public ApiReponse<List<Review>> getAllReview() {
        try {
            return new ApiReponse<>(true, reviewService.getAllReview());
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }


    @GetMapping("/product/{id}")
    public ApiReponse<List<Review>> getAllReviewByProductId(@PathVariable int id) {
        try {
            return new ApiReponse<>(true, reviewService.getAllReviewByProductId(id));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/{id}")
    public ApiReponse<Review> getReviewById(@PathVariable int id) {
        try {
            ReviewEntity reviewEntity = reviewService.getReviewById(id);
            Review review = Format.ReviewEntityToReview(reviewEntity);
            return new ApiReponse<>(true, review);
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PostMapping("/add")
    public ApiReponse<ReviewPost> saveReview(@RequestBody ReviewPost reviewPost) {
        try {
            ReviewEntity reviewEntity = new ReviewEntity();

            reviewEntity.setDelete(false);
            reviewEntity.setCreateDate(new Date());
            reviewEntity.setReview(reviewPost.getReview());
            reviewEntity.setRate(reviewPost.getRate());
            reviewEntity.setStatus(reviewPost.isStatus());

            ProductEntity product = new ProductEntity();
            UserEntity user = new UserEntity();

            product.setId(reviewPost.getProductId());
            user.setId(reviewPost.getUserId());

            reviewEntity.setProduct(product);
            reviewEntity.setUser(user);

            reviewService.saveReview(reviewEntity);
            return new ApiReponse<>(true, reviewPost);
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/update")
    public ApiReponse<ReviewUpdate> updateReview(@RequestBody ReviewUpdate reviewUpdate) {
        if (reviewService.check(reviewUpdate.getId())) {
            ReviewEntity reviewEntity = reviewService.getReviewById(reviewUpdate.getId());
            reviewEntity.setRate(reviewUpdate.getRate());
            reviewEntity.setReview(reviewUpdate.getReview());

            reviewService.updateReview(reviewEntity);

            return new ApiReponse<>(true, reviewUpdate);
        } else {
            return new ApiReponse<>(false, null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiReponse<Boolean> delete(@PathVariable int id) {
        if (reviewService.check(id)) {
            ReviewEntity reviewEntity = reviewService.getReviewById(id);
            reviewEntity.setDelete(true);
            reviewService.updateReview(reviewEntity);
            return new ApiReponse<>(true, true);
        } else {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/status/{id}")
    public ApiReponse<ReviewEntity> updateStatusProduct(@PathVariable int id) {
        ReviewEntity review = reviewService.getReviewById(id);
        review.setStatus(!review.isStatus());
        reviewService.saveReview(review);
        return new ApiReponse<>(true, review);
    }

}
