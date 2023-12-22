package org.example.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carshop.entity.Category;
import org.example.carshop.entity.accout.Account;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    //    @NotBlank(message = "Bạn chưa nhập tên sản phẩm")
//    @Min(value = 3, message = "Tối thiểu 3 ký tự")
//    @Max(value = 100, message = "Tối đa 100 ký tự")
    private String productName;
    //    @NotBlank(message = "Bạn chưa nhập mô tả sản phẩm")
//    @Min(value = 5, message = "Tối thiểu 5 ký tự")
//    @Max(value = 1000, message = "Tối đa 1000 ký tự")
    private String description;
    //    @NotNull(message = "Bạn chưa nhập giá sản phẩm")
//    @DecimalMin(value = "0", message = "Giá tiền không được nhỏ hơn 0")
//    @DecimalMax(value = "100000000", message = "Giá tối đa của sản phẩm là 100 triệu")
    private Double price;
    //    @NotBlank(message = "Bạn chưa chọn ảnh của sản phẩm")
//    @Size(max = 500)
    private int quantity;
    private String avatar;
    private boolean flagDelete;
    private Category category;
    private Account account;

}
