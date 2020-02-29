package application.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "products")
@ApiModel(value = "class Product",
        description = "Сущность продукта которая дана по условию")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ApiModelProperty(value = "ProductName")
    @Size(max = 100)
    @NonNull
    private String name;

    @ApiModelProperty(value = "Description")
    @Size(max = 1000)
    @NonNull
    private String description;
}
