package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.BenhNhan;
import qlpk.repo.BenhNhanRepo;
import qlpk.service.BenhNhanService;

import java.util.List;
import java.util.Optional;

@Service
public class BenhNhanServiceImp implements BenhNhanService {
    @Autowired
    private BenhNhanRepo repo;
    @Override
    public List<BenhNhan> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteBenhNhan(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean saveBenhNhan(BenhNhan benhNhan) {
        repo.save(benhNhan);
        return true;
    }

    @Override
    public boolean updateBenhNhan(BenhNhan benhNhan) {
        repo.save(benhNhan);
        return true;
    }

    @Override
    public BenhNhan searchBenhNhanByCMT(String cmt) {
        return repo.findByCmt(cmt);
    }

    @Override
    public Optional<BenhNhan> findById(int id) {
        return repo.findById(id);
    }
}
