package application.controller;

import application.domain.Product;
import application.service.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsControllerTest {

    @MockBean
    private IProductService iProductService;

    @Autowired
    private ProductsController productsController;

    @Test
    public void product() {
        List<Product> product = productsController.product("^A.*$");
        Assert.assertNotNull(product);
    }
}