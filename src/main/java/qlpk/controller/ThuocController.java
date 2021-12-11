package qlpk.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import qlpk.entity.Thuoc;
import qlpk.service.ThuocService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/qlns")
public class ThuocController {
	@Autowired
	private ThuocService thuocService;
	public ThuocController (ThuocService thuocService) {
		this.thuocService = thuocService;
	};
	@GetMapping("/thuoc/ds-thuoc")
	public String showListThuoc(Model model) {
		List<Thuoc> listThuoc = thuocService.getAll();
		model.addAttribute("listThuoc", listThuoc);		
		return "QuanLyNhanSu/ListMedicine";
	}
		
	@GetMapping("/thuoc/add")
	public String showFormAddThuoc(Model model) {
		Thuoc thuoc = new Thuoc();
		model.addAttribute("thuoc", thuoc);
		return "QuanLyNhanSu/AddMedicine";
	}
	
	@PostMapping("/thuoc/add")
	public String handleAddThuoc(@Valid @ModelAttribute("thuoc") Thuoc thuoc,  BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "QuanLyNhanSu/AddMedicine";
		}
		thuocService.saveThuoc(thuoc);
		return "redirect:/qlns/thuoc/ds-thuoc";
	}
	
	@GetMapping("/thuoc/edit/{id}")
	public String showEditThuoc(@PathVariable int id, Model model) {

			Thuoc thuoc = thuocService.getById(id);
			model.addAttribute("thuoc", thuoc);
			return "QuanLyNhanSu/EditMedicine";


	}
	
	@PostMapping("/thuoc/edit/{id}") 
	public String handleEditThuoc(@Valid @ModelAttribute("thuoc") Thuoc thuoc, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "QuanLyNhanSu/EditMedicine";
		}
		thuocService.updateThuoc(thuoc);
		return "redirect:/qlns/thuoc/ds-thuoc";
	}
	
	@GetMapping("/thuoc/delete/{id}")
	public String deleteThuoc(@PathVariable int id) {
			thuocService.deleteThuoc(id);
			return "redirect:/qlns/thuoc/ds-thuoc";
		
	}
	
}