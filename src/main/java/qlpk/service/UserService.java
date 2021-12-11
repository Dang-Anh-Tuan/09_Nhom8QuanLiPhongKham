package qlpk.service;


import qlpk.dto.UserDTO;
import qlpk.entity.User;

public interface UserService {
    UserDTO getUserByID(int id);
    UserDTO getUserByName(String username);
    boolean save(UserDTO userDTO);
    boolean delete(User user);
}
