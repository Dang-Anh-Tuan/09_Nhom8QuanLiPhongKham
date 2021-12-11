package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.DonThuoc;
import qlpk.repo.DonThuocRepo;
import qlpk.service.DonThuocService;

import java.util.List;
import java.util.Optional;

@Service
public class DonThuocServiceImp implements DonThuocService {
    @Autowired
    private DonThuocRepo donThuocRepo;

    @Override
    public Integer saveThuoc(DonThuoc donThuoc) {
        DonThuoc donThuoc1 = donThuocRepo.save(donThuoc);
        return donThuoc1.getId();
    }

    @Override
    public Optional<DonThuoc> getById(int id) {
        return donThuocRepo.findById(id);
    }

    @Override
    public DonThuoc getByIdDelete(int id) {
        return donThuocRepo.findDonThuocByIdAndIsDelete(id, false);
    }

    @Override
    public void delete(DonThuoc donThuoc) {
        DonThuoc donThuoc1 = donThuocRepo.getById(donThuoc.getId());
        donThuoc1.setDelete(true);
        donThuocRepo.save(donThuoc);
    }

    @Override
    public List<DonThuoc> getAll() {
        return donThuocRepo.findDonThuocByIsDelete(false);
    }
}
