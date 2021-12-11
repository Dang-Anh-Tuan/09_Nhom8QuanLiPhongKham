package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.DonThuoc;

import java.util.List;

@Repository
public interface DonThuocRepo extends JpaRepository<DonThuoc, Integer> {
    DonThuoc findDonThuocByIdAndIsDelete(int id, boolean isDelete);
    List<DonThuoc> findDonThuocByIsDelete(boolean isDelete);
}
