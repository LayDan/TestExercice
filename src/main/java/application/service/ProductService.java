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
    private final List<Product> productList;

    //if (regular.startsWith("^")), потому что по условию задачи значение данного спецсимвола было указано как "НЕ"
    //Использывание параллельных потоков было выбранно из-за того, что при работе с большим объемом данных последовательная итерация проигрывает
    //в скорости.
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

    //По поводу листов которые желательно использовать для потоков из библиотеки java.util.concurrent.*
    //я просто не особо их применял так как практики по многопоточности особо не было, но в данном случае я их решил откинуть из-за того
    //что ничего не сказано про изменения в БД.

    //В идеале добавить метод который будет хранить переменную массива какое-то время и с помощью @Scheduled обновлять её
    // если будет возможность добавлять и изменять содержимое БД,
    //однако пока что я не знаю как толком это реализовать та и по условию этого делать не нужно.
}
