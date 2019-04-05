package agixis.service.proxy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Company {
    private Long id;
    private String name;
    private Double price;

}
