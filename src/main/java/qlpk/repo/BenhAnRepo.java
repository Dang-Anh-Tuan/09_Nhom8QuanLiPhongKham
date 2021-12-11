package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qlpk.entity.BenhAn;
import qlpk.entity.YTa;

import java.util.List;

@Repository
public interface BenhAnRepo extends JpaRepository<BenhAn, Integer> {
    List<BenhAn> findBenhAnByIsDelete(boolean isDelete);
}
