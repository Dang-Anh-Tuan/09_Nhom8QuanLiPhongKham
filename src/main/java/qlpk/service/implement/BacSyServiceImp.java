package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.entity.BenhAn;
import qlpk.modelUtil.BacSyLuong;
import qlpk.repo.BacSyRepo;
import qlpk.repo.BenhAnRepo;
import qlpk.repo.BenhRepo;
import qlpk.repo.UserRepo;
import qlpk.service.BacSyService;
import qlpk.service.BenhAnService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class BacSyServiceImp implements BacSyService {
    @Autowired
    private BacSyRepo bacSyRepo;
    @Autowired
    private BenhRepo benhRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BenhAnRepo benhAnRepo;
    @Override
    public void deleteBacSy(int id) {
        BacSy bacSy = bacSyRepo.getById(id);
        bacSy.setDelete(true);
        bacSyRepo.save(bacSy);
    }

    @Override
    public boolean saveBacSy(BacSy bacSy, UserDTO userDTO) {
        bacSy.setUser(userRepo.findByUserName(userDTO.getUserName()));
        bacSyRepo.save(bacSy);
        return true;
    }

    @Override
    public boolean updateBacSy(BacSy bacSy) {
        bacSyRepo.save(bacSy);
        return true;
    }

    @Override
    public BacSy searchBacSyByCMT(String cmt) {
        return bacSyRepo.findByCmt(cmt);
    }

    @Override
    public List<BacSy> getAll() {
        return bacSyRepo.findBacSyByIsDelete(false);
    }

	@Override
	public Optional<BacSy> getById(int id) {
		return bacSyRepo.findBacSyByIdAndIsDelete(id, false);
	}

    @Override
    public List<Benh> getListBenhByBacSy(int id) {
        return benhRepo.findBenhByBacSy(bacSyRepo.findById(id));
    }

    @Override
    public BacSy getByUsername(String username) {
        return bacSyRepo.findBacSyByUserAndIsDelete(userRepo.findByUserName(username), false);
    }


    @Override
    public List<BacSyLuong> tinhLuongBacSy(Date sdate, Date edate) {
        List<BacSy> listBacSi = getAll();
        List<BacSyLuong> listBacSiLuong = new ArrayList<>();
        for(BacSy bacSy:listBacSi){
            BacSyLuong bacSyLuong = new BacSyLuong();
            bacSyLuong.setBacSy(bacSy);
            bacSyLuong.setLuong(bacSyRepo.tinhLuongBacSy(bacSy.getId(), sdate, edate).get(0));
            listBacSiLuong.add(bacSyLuong);
        }
        return listBacSiLuong;
    }
}
