package com.adan.productcommandservice.service;

import com.adan.productcommandservice.dto.ProductEvent;
import com.adan.productcommandservice.entity.Product;
import com.adan.productcommandservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event=new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

    public Product updateProduct(long id,ProductEvent productEvent){
        Product existingProduct = repository.findById(id).get();
        Product newProduct=productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());
        Product productDO = repository.save(existingProduct);
        ProductEvent event=new ProductEvent("UpdateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

}
