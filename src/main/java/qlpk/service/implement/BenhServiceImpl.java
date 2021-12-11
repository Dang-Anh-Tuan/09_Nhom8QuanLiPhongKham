package qlpk.service.implement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlpk.entity.Benh;
import qlpk.modelUtil.ThongkeBenhDetail;
import qlpk.repo.BenhRepo;
import qlpk.service.BenhService;

@Service
public class BenhServiceImpl implements BenhService {

	@Autowired
	private BenhRepo benhRepo;

	public BenhServiceImpl(BenhRepo benhRepo) {
		this.benhRepo = benhRepo;
	}

	@Override
	public void deleteBenh(int id) {
		Benh benh = benhRepo.getById(id);
		benh.setDelete(true);
		saveBenh(benh);
	}

	@Override
	public boolean saveBenh(Benh benh) {
		benhRepo.save(benh);
		return true;
	}

	@Override
	public boolean updateBenh(Benh benh) {
		benhRepo.save(benh);
		return true;
	}

	@Override
	public List<Benh> getAll() {
		return benhRepo.findBenhByIsDelete(false);
	}

	@Override
	public Optional<Benh> getById(int id) {
		return benhRepo.findById(id);
	}

	@Override
	public List<Benh> getBenhByBacSy(int idbacsy) {
		return benhRepo.findBenhByBacSy_IdAndIsDelete(idbacsy, false);
	}

	@Override
	public List<ThongkeBenhDetail> thongKeBenh(Date sdate, Date edate) {
		List<Benh> listBenh = benhRepo.findAll();
		List<ThongkeBenhDetail> listThongKeBenh = new ArrayList<>();

		for (Benh benh : listBenh) {
			ThongkeBenhDetail thongkeBenhDetail = new ThongkeBenhDetail();
			thongkeBenhDetail.setBenh(benh);
			thongkeBenhDetail.setSoCa(benhRepo.thongKeBenh(benh.getId(), sdate, edate).get(0));
			listThongKeBenh.add(thongkeBenhDetail);
		}
		listThongKeBenh.sort(new Comparator<ThongkeBenhDetail>() {
			@Override
			public int compare(ThongkeBenhDetail o1, ThongkeBenhDetail o2) {
				return   o2.getSoCa() - o1.getSoCa();
			}
		});
		return listThongKeBenh;
	}

}
