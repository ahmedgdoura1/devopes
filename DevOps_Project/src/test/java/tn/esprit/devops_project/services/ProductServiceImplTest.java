package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.Iservices.IProductService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceImplTest {


    @Autowired
    private IProductService iProductService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testAddProduct() {
        // Create a sample stock
        Stock stock = new Stock(1L, "EXIST", null);
        Stock savedStock = stockRepository.save(stock);

        // Create a sample product
        Product product = new Product();
        product.setTitle("T-shirt");
        product.setPrice(320);
        product.setQuantity(50);
        product.setCategory(ProductCategory.CLOTHING);

        // Add product with the created stock's ID
        Product savedProduct = iProductService.addProduct(product, savedStock.getIdStock());

        // Ensure the saved stock and product are not null
        assertNotNull(savedStock);
        assertNotNull(savedProduct);

        // Ensure product properties are correctly set
        assertEquals("T-shirt", savedProduct.getTitle());
        assertEquals(320, savedProduct.getPrice());
        assertEquals(50, savedProduct.getQuantity());
        assertEquals(ProductCategory.CLOTHING, savedProduct.getCategory());
        assertEquals(savedStock, savedProduct.getStock());
    }

    // Write similar test methods for other service operations like retrieve, update, delete, etc.
    // For example:

    @Test
    public void testRetrieveProduct() {
        // Add a sample product
        Stock stock = new Stock(1L, "DELL", null);
        Stock savedStock = stockRepository.save(stock);
        Product product = new Product();
        product.setTitle("Laptop");
        product.setPrice(1000);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);
        product.setStock(savedStock);
        Product savedProduct = productRepository.save(product);

        // Retrieve the product
        Product retrievedProduct = iProductService.retrieveProduct(savedProduct.getIdProduct());

        // Ensure the retrieved product matches the saved product
        assertNotNull(retrievedProduct);
        assertEquals(savedProduct.getIdProduct(), retrievedProduct.getIdProduct());
        assertEquals(savedProduct.getTitle(), retrievedProduct.getTitle());
        // Add assertions for other fields as well
    }
}