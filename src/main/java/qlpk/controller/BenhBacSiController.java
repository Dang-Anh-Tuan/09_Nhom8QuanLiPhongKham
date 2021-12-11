package qlpk.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.modelUtil.BenhBacSi;
import qlpk.service.BacSyService;
import qlpk.service.BenhService;

@Controller
public class BenhBacSiController {

	@Autowired
	private BacSyService bacSyService;

	@Autowired
	private BenhService benhService;

	public BenhBacSiController(BacSyService bacSyService, BenhService benhService) {
		this.bacSyService = bacSyService;
		this.benhService = benhService;
	}

	@GetMapping("/qlns/benh-bacsi/{id}")
	public String showFormBenhBacSi(@PathVariable Integer id, Model model) {
		List<Benh> dsBenh = benhService.getAll();

		BenhBacSi benhBacSi = new BenhBacSi();

		benhBacSi.setDsBenh(dsBenh);

		model.addAttribute("benhBacSi", benhBacSi);
		return "QuanLyNhanSu/BacSiBenh";
	}

	@PostMapping("/qlns/benh-bacsi/{id}")
	public String handleAddBenhToBacSi(
			@PathVariable Integer id, 
			@ModelAttribute("benhBacSi") BenhBacSi benhBacSi) {
			List<Benh> benhChon = benhBacSi.getDsBenhChon();
			Optional<BacSy> bacSy = bacSyService.getById(id);
			if(bacSy.isPresent()){
				for(Benh benh : benhChon) {
					benh.setBacSy(bacSy.get());
					benhService.updateBenh(benh);
				}
//				bacSy.get().setBenh(benhSet);
//				bacSyService.updateBacSy(bacSy.get());
			}
			return "redirect:/qlns/bacsi/ds-bacsi";
	}

}
