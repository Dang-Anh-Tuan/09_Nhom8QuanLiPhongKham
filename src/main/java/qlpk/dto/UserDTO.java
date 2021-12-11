package qlpk.dto;

import lombok.*;
import qlpk.entity.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//cái này dùng để test object gửi từ form qua
public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private Role role;
}
