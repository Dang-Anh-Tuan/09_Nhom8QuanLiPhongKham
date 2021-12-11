package qlpk.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import qlpk.modelUtil.DateLuong;
import qlpk.modelUtil.ThongkeBenhDetail;
import qlpk.service.BenhService;

@Controller
public class ThongKeBenhController {
	
	@Autowired
	private BenhService benhService;
	
	
	
	public ThongKeBenhController(BenhService benhService) {
		this.benhService = benhService;
	}
	
	@GetMapping("/qlns/thongkebenh")
	public String showThongKe(Model model) {
		DateLuong dateLuong = new DateLuong();
		model.addAttribute("dateLuong", dateLuong);
		return "QuanLyNhanSu/ViewThongKeChonNgay";
	}
	
	@PostMapping("/qlns/thongkebenh")
	public String handelThongKe(Model model, @ModelAttribute("dateLuong") DateLuong dateLuong) {
		List<ThongkeBenhDetail> thongKeBenh = benhService.thongKeBenh(dateLuong.getNgayBD(), dateLuong.getNgayKT());
		model.addAttribute("thongKeBenh", thongKeBenh);
		return "QuanLyNhanSu/ViewThongKe";
	}
}
