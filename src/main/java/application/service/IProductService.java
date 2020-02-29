package application.service;

import application.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProductsByRegular(String reg);
}
