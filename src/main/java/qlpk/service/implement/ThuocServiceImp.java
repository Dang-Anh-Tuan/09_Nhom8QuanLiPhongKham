package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.Thuoc;
import qlpk.repo.ThuocRepo;
import qlpk.service.ThuocService;

import java.util.List;

@Service
public class ThuocServiceImp implements ThuocService {
    @Autowired
    private ThuocRepo repo;
    @Override
    public void deleteThuoc(int id) {
        Thuoc thuoc = repo.getById(id);
        thuoc.setDelete(true);
        saveThuoc(thuoc);
    }

    @Override
    public boolean saveThuoc(Thuoc thuoc) {
        repo.save(thuoc);
        return true;
    }

    @Override
    public boolean updateThuoc(Thuoc thuoc) {
        repo.save(thuoc);
        return true;
    }

    @Override
    public Thuoc searchThuocByName(String name) {
        return repo.findByName(name);
    }


    @Override
    public List<Thuoc> getAll() {
        return repo.findAllByIsDelete(false);
    }

    @Override
    public Thuoc getById(int id) {
        return repo.findThuocByIdAndIsDelete(id, false);
    }
}
