package qlpk.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import qlpk.entity.Benh;
import qlpk.service.BenhService;

@Controller
@RequestMapping("/qlns")
public class BenhController {
	@Autowired
	private BenhService benhService;
	
	public BenhController(BenhService benhService) {
		this.benhService = benhService;
	}
	
	@GetMapping("/benh/ds-benh")
	public String showListBenh(Model model) {
		List<Benh> listBenh = benhService.getAll();
		model.addAttribute("listBenh", listBenh);		
		return "QuanLyNhanSu/ListBenh";
	}
	
	@GetMapping("/benh/add")
	public String showFormAddBenh(Model model) {
		Benh benh = new Benh();
		model.addAttribute("benh", benh);
		return "QuanLyNhanSu/AddBenh";
	}
	
	@PostMapping("/benh/add")
	public String handleAddBenh(@Valid @ModelAttribute("benh") Benh benh,  BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "QuanLyNhanSu/AddBenh";
		}
		benhService.saveBenh(benh);
		return "redirect:/qlns/benh/ds-benh";
	}
	
	@GetMapping("/benh/edit/{id}")
	public String showEditBenh(@PathVariable int id, Model model) {
		Optional<Benh> optBenh = benhService.getById(id);
		if(optBenh.isPresent()) {
			Benh benh = optBenh.get();
			model.addAttribute("benh", benh);
			return "QuanLyNhanSu/EditBenh";
		}
		
		return "redirect:/404";
	}
	
	@PostMapping("/benh/edit/{id}") 
	public String handleEditBenh(@Valid @ModelAttribute("benh") Benh benh, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "QuanLyNhanSu/EditMedicine";
		}
		benhService.saveBenh(benh);
		return "redirect:/qlns/benh/ds-benh";
	}
	
	@GetMapping("/benh/delete/{id}")
	public String deleteBenh(@PathVariable int id) {
		Optional<Benh> optBenh = benhService.getById(id);
		if(optBenh.isPresent()) {
			benhService.deleteBenh(id);
			return "redirect:/qlns/benh/ds-benh";
		}
		
		return "redirect:/404";
		
	}
}
