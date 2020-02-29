package application.service;

import application.domain.Product;
import application.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    public ProductService(ProductRepository productRepository) {
        this.productList = productRepository.findAll();
    }

    //final потому что задача не подразумевает изменение таблицы
    // С помощью финального свойства мы не затрачиваем время и ресурсы чтобы каждый раз доставать массив из репозитория.
    private final List<Product> productList;


    //if (regular.startsWith("^")), потому что по условию задачи значение данного спецсимвола было указано как "НЕ"
    //Использывание параллельных потоков было выбранно из-за того, что при работе с большим объемом данных последовательная итерация проигрывает
    //в скорости и производительности.
    @ApiOperation(value = "Find products by regular", httpMethod = "Get")
    @Override
    public List<Product> getProductsByRegular(String regular) {
        if (regular.startsWith("^")) {
            return productList.parallelStream()
                    .filter(product -> !product.getName().matches(regular) && !product.getName().equals(regular))
                    .collect(Collectors.toList());
        } else {
            return productList.parallelStream()
                    .filter(product -> product.getName().matches(regular) && !product.getName().equals(regular))
                    .collect(Collectors.toList());
        }
    }
}
