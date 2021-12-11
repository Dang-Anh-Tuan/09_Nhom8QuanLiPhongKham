package qlpk.service;

import qlpk.entity.Thuoc;

import java.util.List;

public interface ThuocService {
    void deleteThuoc(int id);
    boolean saveThuoc(Thuoc thuoc);
    boolean updateThuoc(Thuoc thuoc);
    Thuoc searchThuocByName(String name);
    List<Thuoc> getAll();
    Thuoc getById(int id);
}
