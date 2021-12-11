package qlpk.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import qlpk.entity.Benh;
import qlpk.modelUtil.ThongkeBenhDetail;

public interface BenhService {
	void deleteBenh(int id);
    boolean saveBenh(Benh benh);
    boolean updateBenh(Benh benh);
    List<Benh> getAll();
    Optional<Benh> getById(int id);
    List<Benh> getBenhByBacSy(int idbacsy);
    List<ThongkeBenhDetail> thongKeBenh(Date sdate, Date edate);
}
