package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BacSyRepo extends JpaRepository<BacSy, Integer> {
    @Query("SELECT bs from BacSy bs where bs.cmt = ?1")
    BacSy findByCmt(String cmt);
    BacSy findBacSyByUserAndIsDelete(User user, boolean isDelete);
    Optional<BacSy> findBacSyByIdAndIsDelete(int id, boolean isDelete);
    List<BacSy> findBacSyByIsDelete(boolean isDelete);
    @Query(value = "CALL get_salary_doctor(:did, :sdate, :edate);", nativeQuery = true)
    public List<Float> tinhLuongBacSy(@RequestParam("did") int did, @RequestParam("sdate") Date sdate, @RequestParam("edate") Date edate);


}
