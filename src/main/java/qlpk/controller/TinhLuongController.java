package qlpk.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import qlpk.modelUtil.BacSyLuong;
import qlpk.modelUtil.DateLuong;
import qlpk.modelUtil.YtaLuong;
import qlpk.service.BacSyService;
import qlpk.service.YTaService;

@Controller
public class TinhLuongController {

	@Autowired
	private BacSyService bacSyService;
	@Autowired
	private YTaService yTaService;
	public TinhLuongController(BacSyService bacSyService, YTaService yTaService){
		this.bacSyService = bacSyService;
		this.yTaService = yTaService;
	}

	@GetMapping("/qlns/xemluong-bs")
	public String showViewTinhLuong(Model model) {
		DateLuong dateLuong = new DateLuong();
		model.addAttribute("dateLuong", dateLuong);
		return "QuanLyNhanSu/SalaryBacSi";
	}
	
	@PostMapping("/qlns/xemluong-bs")
	public String resultTinhLuong(@ModelAttribute("dateLuong") DateLuong dateLuong, Model model) {
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayBD()));
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayKT()));
		List<BacSyLuong>  dsBacSyLuong = bacSyService.tinhLuongBacSy(dateLuong.getNgayBD(), dateLuong.getNgayKT());
		model.addAttribute("dsBacSiLuong", dsBacSyLuong);
		// model add ds dsBacSiLuong
		return "QuanLyNhanSu/SalaryResultBacSi";
	}
	

	@GetMapping("/qlns/xemluong-yta")
	public String showViewTinhLuongYTa(Model model) {
		DateLuong dateLuong = new DateLuong();
		model.addAttribute("dateLuong", dateLuong);
		return "QuanLyNhanSu/SalaryYTa";
	}
	
	@PostMapping("/qlns/xemluong-yta")
	public String resultTinhLuongYTa(@ModelAttribute("dateLuong") DateLuong dateLuong, Model model) {
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayBD()));
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayKT()));
		List<YtaLuong> dsYtaLuong = yTaService.tinhLuongYta(dateLuong.getNgayBD(), dateLuong.getNgayKT());

		model.addAttribute("dsYTaLuong", dsYtaLuong);
		// model add ds dsBacSiLuong
		return "QuanLyNhanSu/SalaryResultYTa";
	}
}
