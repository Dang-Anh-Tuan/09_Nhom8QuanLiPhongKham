package qlpk.service;

import qlpk.entity.BenhNhan;
import qlpk.entity.YTa;

import java.util.List;
import java.util.Optional;

public interface BenhNhanService {
    List<BenhNhan> findAll();
    void deleteBenhNhan(int id);
    boolean saveBenhNhan(BenhNhan benhNhan);
    boolean updateBenhNhan(BenhNhan benhNhan);
    BenhNhan searchBenhNhanByCMT(String cmt);
    Optional<BenhNhan> findById(int id);
}
