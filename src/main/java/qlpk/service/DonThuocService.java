package qlpk.service;

import qlpk.entity.DonThuoc;
import qlpk.entity.Thuoc;

import java.util.List;
import java.util.Optional;

public interface DonThuocService {
    Integer saveThuoc(DonThuoc donThuoc);
    Optional<DonThuoc> getById(int id);
    DonThuoc getByIdDelete(int id);
    void delete(DonThuoc donThuoc);
    List<DonThuoc> getAll();
}
