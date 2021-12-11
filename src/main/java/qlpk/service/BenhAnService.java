package qlpk.service;

import java.util.List;
import java.util.Optional;

import qlpk.entity.BenhAn;

public interface BenhAnService {
	List<BenhAn> getAll();
	
	boolean saveBenhAn(BenhAn benhAn);
	
	boolean updateBenhAn(BenhAn benhAn);

	Optional<BenhAn> getById(int id);
	
	void deleteBenhAn(int id);

}
