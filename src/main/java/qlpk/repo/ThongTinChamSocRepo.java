package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.ThongTinChamSoc;
@Repository
public interface ThongTinChamSocRepo extends JpaRepository<ThongTinChamSoc, Integer> {
}
