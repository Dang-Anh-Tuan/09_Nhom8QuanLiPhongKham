package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import qlpk.entity.BacSy;
import qlpk.entity.User;
import qlpk.entity.YTa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface YTaRepo extends JpaRepository<YTa, Integer> {
    @Query("SELECT y from YTa y where y.cmt = ?1")
    YTa findByCmt(String cmt);
    YTa findYTaByUserAndIsDelete(User user, boolean isDelete);
    Optional<YTa> findYTaByIdAndIsDelete(int id, boolean isDelete);
    YTa findYTaByUser(User user);
    List<YTa> findYTaByIsDelete(boolean isDelete);
    @Query(value = "CALL get_salary_nurse(:nid, :sdate, :edate);", nativeQuery = true)
    public List<Float> tinhLuongYta(@RequestParam("nid") int nid, @RequestParam("sdate") Date sdate, @RequestParam("edate") Date edate);

}
