package by.itechart.mapping.dto.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {

    private Long id;

    private String name;

    public AuthorityDto(String name) {
        this.name = name;
    }
}
