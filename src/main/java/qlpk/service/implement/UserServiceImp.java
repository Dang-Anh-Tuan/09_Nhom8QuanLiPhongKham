package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qlpk.dto.UserDTO;
import qlpk.entity.User;
import qlpk.repo.UserRepo;
import qlpk.service.UserService;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDTO getUserByID(int id) {
        User user = userRepo.getById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        return userDTO;
    }

    @Override
    public UserDTO getUserByName(String username) {
        User user = userRepo.findByUserName(username);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public boolean save(UserDTO userDTO) {
        User user;
        if(userDTO.getId()!=null) {
            user = userRepo.getById(userDTO.getId());
        }
        else{
            user = new User();
            user.setUserName(userDTO.getUserName());
        }
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        userRepo.delete(user);
        return true;
    }
}
