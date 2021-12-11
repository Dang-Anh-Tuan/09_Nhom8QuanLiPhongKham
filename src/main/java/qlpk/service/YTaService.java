package qlpk.service;

import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;
import qlpk.modelUtil.BacSyLuong;
import qlpk.modelUtil.YtaLuong;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface YTaService {
    List<YTa> findAll();
    void deleteYTa(int id);
    boolean saveYTa(YTa yTa, UserDTO userDTO);
    boolean updateYTa(YTa yTa);
    YTa searchYTaByCMT(String cmt);
    Optional<YTa> getById(int id);
    List<YtaLuong> tinhLuongYta(Date sdate, Date edate);
    YTa getByUsername(String username);
}
