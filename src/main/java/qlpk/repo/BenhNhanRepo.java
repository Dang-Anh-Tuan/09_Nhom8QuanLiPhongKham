package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;
import qlpk.entity.BenhNhan;

@Repository
public interface BenhNhanRepo extends JpaRepository<BenhNhan, Integer> {
    @Query("SELECT bn from BenhNhan bn where bn.cmt = ?1")
    BenhNhan findByCmt(String cmt);
}
