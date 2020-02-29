package application.controller;

import application.domain.Product;
import application.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {
    private IProductService iProductService;

    public ProductsController(IProductService iProductService) {
        this.iProductService = iProductService;
    }


    //Свойство nameFilter обязательно

    @GetMapping(value = "/shop/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> product(@RequestParam(name = "nameFilter") String nameFilter) {
        return iProductService.getProductsByRegular(nameFilter);
    }

}

