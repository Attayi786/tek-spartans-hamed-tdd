package tek.tdd.api.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenRequest {

    private String username;
    private String password;
}