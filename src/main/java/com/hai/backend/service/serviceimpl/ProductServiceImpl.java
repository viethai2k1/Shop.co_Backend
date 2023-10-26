package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.ProductEntity;
import com.hai.backend.modal.Review;
import com.hai.backend.repository.ProductRepository;
import com.hai.backend.service.ProductService;
import com.hai.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewService reviewService;

    @Override
    public List<ProductEntity> getAll() {
        List<ProductEntity> products = productRepository.findAllByIsDeleteIsFalse();
        for(ProductEntity product : products){
            List<Review> reviews = reviewService.getAllReviewByProductId(product.getId());

            double total = 0;
            for (Review review:reviews) {
                total += review.getRate();
            }
            double avg = total / reviews.size();
            product.setRate(avg);
            product.setTotalRate(reviews.size());
        }

        return products;
    }

    @Override
    public List<ProductEntity> getAllByCategory(int id) {
        List<ProductEntity> list = productRepository.findByCategories_Id(id);
        for(ProductEntity product : list){
            List<Review> reviews = reviewService.getAllReviewByProductId(product.getId());

            double total = 0;
            for (Review review:reviews) {
                total += review.getRate();
            }
            double avg = total / reviews.size();
            product.setRate(avg);
            product.setTotalRate(reviews.size());
        }
        return list;
    }


    @Override
    public List<ProductEntity> search(String query) {
        return productRepository.searchByName(query);
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return  productRepository.save(productEntity);
    }

    @Override
    public ProductEntity getProductById(int id) {
        ProductEntity product = productRepository.findById(id).get();
        if(product.isDelete()){
            return null;
        }

        return product;
    }

    @Override
    public Boolean check(int id) {
        return productRepository.existsById(id);
    }
}
