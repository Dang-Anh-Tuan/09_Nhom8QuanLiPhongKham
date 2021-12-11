package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import qlpk.entity.BacSy;
import qlpk.entity.Benh;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BenhRepo extends JpaRepository<Benh, Integer> {
	List<Benh> findBenhByBacSy(Optional<BacSy> idbacsy);

	List<Benh> findBenhByBacSy_IdAndIsDelete(int idbacSy, boolean isDelete);

	List<Benh> findBenhByIsDelete(boolean isDelete);

	@Query(value = "CALL list_benh(:bid, :sdate, :edate);", nativeQuery = true)
	public List<Integer> thongKeBenh(@RequestParam("bid") int bid, @RequestParam("sdate") Date sdate,
			@RequestParam("edate") Date edate);
}
