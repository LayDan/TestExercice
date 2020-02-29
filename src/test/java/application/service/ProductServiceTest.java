package application.service;

import application.domain.Product;
import application.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


//    При записи в переменную время обработки с 2-3 ms возростает до 8119 ms +
//    Поэтому было принято решение использовать final переменную в Service, чтобы сократить количество запросов к БД
//    Тем самым скорость обработки возросла
//    Время обработки без List<Product> all = productRepository.findAll();  = 2-3 ms
//    Время обработки c List<Product> all = productRepository.findAll();  = 8119 ms +

    @Test
    public void getProductsByRegular() {
        long start = System.currentTimeMillis();

//      List<Product> all = productRepository.findAll();

        List<Product> productsByRegular = productService.getProductsByRegular("^q.*$");
        long finish = System.currentTimeMillis();
        log.info(finish - start + " ms");
        Assert.assertNotNull(productsByRegular);
    }
}