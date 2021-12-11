package qlpk.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import qlpk.dto.UserDTO;
import qlpk.entity.*;
import qlpk.entity.enums.Role;
import qlpk.modelUtil.BacSyLuong;
import qlpk.modelUtil.BenhDanhSachBenh;
import qlpk.modelUtil.DetailThuoc;
import qlpk.security.CustomUserDetails;
import qlpk.service.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class DoctorController {

	@Autowired
	private BacSyService bacSyService;
	@Autowired
	private BenhAnService benhAnService;
	@Autowired
	private UserService userService;
	@Autowired
	private BenhService benhService;
	@Autowired
	private ThuocService thuocService;
	@Autowired
	private DonThuocService donThuocService;

	public DoctorController(BacSyService bacSyService, UserService userService,
							BenhAnService benhAnService, BenhService benhService,
							ThuocService thuocService, DonThuocService donThuocService) {
		this.bacSyService = bacSyService;
		this.benhAnService = benhAnService;
		this.userService = userService;
		this.benhService = benhService;
		this.thuocService = thuocService;
		this.donThuocService = donThuocService;
	}

	@GetMapping("/qlns/bacsi/ds-bacsi")
	public String showListBacSi(Model model) {
		List<BacSy> dsBacSi = bacSyService.getAll();
		model.addAttribute("dsBacSi", dsBacSi);
		return "QuanLyNhanSu/ListDoctor";
	}

	@GetMapping("/qlns/bacsi/add")
	public String showAddFormBacSi(Model model) {
		BacSy bacSi = new BacSy();
		UserDTO user = new UserDTO();
		model.addAttribute("bacsi", bacSi);
		model.addAttribute("taikhoan", user);
		return "QuanLyNhanSu/AddDoctor";

	}

	@PostMapping("/qlns/bacsi/add")
	public String handleAddBacSi(@Valid @ModelAttribute("bacsi") BacSy bacsi, BindingResult result,
								 @ModelAttribute("taikhoan") UserDTO userDTO) {

		if (result.hasErrors()) {
			return "QuanLyNhanSu/AddDoctor";
		}

		userDTO.setRole(Role.BACSY);
		userService.save(userDTO);
		bacSyService.saveBacSy(bacsi, userDTO);

		return "redirect:/qlns/bacsi/ds-bacsi";
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		UserDTO userDTO;

		if (optBacSi.isPresent()) {
			userDTO = userService.getUserByID(optBacSi.get().getUser().getId());
			model.addAttribute("bacsi", optBacSi.get());
			model.addAttribute("taikhoan", userDTO);

			return "QuanLyNhanSu/EditDoctor";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/bacsi/edit/{id}")
	public String handleEditBacSi(@Valid @ModelAttribute("bacsi") BacSy bacsi, BindingResult result,
			@ModelAttribute("taikhoan") UserDTO userDTO) {
		if (result.hasErrors()) {
			return "QuanLyNhanSu/EditDoctor";
		}

		bacSyService.updateBacSy(bacsi);
		return "redirect:/qlns/bacsi/ds-bacsi";
	}

	@GetMapping("/qlns/bacsi/delete/{id}")
	public RedirectView handleDeleteBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		

		if (optBacSi.isPresent()) {
			bacSyService.deleteBacSy(id);
			return redirectView;
		}

		redirectView.setUrl("/404");
		return redirectView;
	}

	@GetMapping("/bacsi/khambenh/{id}")
	public String showViewKhamBenh(@PathVariable int id, Model model, Authentication authentication) {
		// get Benh An
		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			BacSy bacSy = bacSyService.getByUsername(userDetails.getUsername());

			List<Benh> dsBenh = benhService.getBenhByBacSy(bacSy.getId());

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);


			Benh benh = new Benh();

			BenhDanhSachBenh benhDanhSachBenh = new BenhDanhSachBenh();
			benhDanhSachBenh.setIdBenh(benh.getId());
			benhDanhSachBenh.setDsBenh(dsBenh);

			model.addAttribute("benhDanhSachBenh", benhDanhSachBenh);
			return "BacSi/KhamBenh";
		}
		return "redirect:/404";

	}

	@PostMapping("/bacsi/khambenh/{id}")
	public String handleKhamBenh(@PathVariable int id, @ModelAttribute("benhAn") BenhAn benhAn,
			@ModelAttribute("benhNhan") BenhNhan benhNhan,
			Authentication authentication,
			@ModelAttribute("benhDanhSachBenh") BenhDanhSachBenh benhDanhSachBenh) {
		Optional<BenhAn> benhAn1 = benhAnService.getById(id);
		if(benhAn1.isPresent()){
			benhAn1.get().setIdBenh(benhDanhSachBenh.getIdBenh());
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			BacSy bacSy = bacSyService.getByUsername(userDetails.getUsername());
			benhAn1.get().setBacSy(bacSy);
			benhAnService.updateBenhAn(benhAn1.get());

		}
		return "redirect:/bacsi/list-benhan";
	}

	@GetMapping("/bacsi/kedon/{id}")
	public String showViewKeDon(@PathVariable int id, Model model) {

		// model add benh anh
		// model add ds-thuoc
		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);

			// fake thuoc

			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add thuoc
			model.addAttribute("dsThuoc", dsThuoc);

			return "BacSi/KeDon";
		}
		return "redirect:/404";

	}

	@PostMapping("/bacsi/kedon/{id}")
	public String handleKeDon(@PathVariable int id, @RequestBody(required = false) String json, Model model) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			BenhAn benhAn = benhAnService.getById(id).get();
			String dsDonThuoc = "";
			List<DetailThuoc> listDetailThuoc = mapper.readValue(json, new TypeReference<List<DetailThuoc>>() {
			});
			for (DetailThuoc detailThuoc: listDetailThuoc
				 ) {
				DonThuoc donThuoc = new DonThuoc();
				donThuoc.setThuoc(thuocService.getById(detailThuoc.getIdThuoc()));
				donThuoc.setLieuLuong(detailThuoc.getLieuDung()+"");
				donThuoc.setCacDung(detailThuoc.getCachDung());
				donThuoc.setTongTien(thuocService.getById(detailThuoc.getIdThuoc()).getGia()*detailThuoc.getLieuDung());
				Integer iddonthuoc = donThuocService.saveThuoc(donThuoc);
				dsDonThuoc = dsDonThuoc + iddonthuoc + " ";
			}
			benhAn.setDsDonThuoc(dsDonThuoc);
			benhAn.setDaPhat(false);
			benhAnService.saveBenhAn(benhAn);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/qlns/bacsi/ds-bacsi";
	}

	@GetMapping(value = { "/bacsi/xembenhan/{id}" })
	public String showBSBenhAn(@PathVariable int id, Model model) {
		// get Benh An
		// get ds benh cua benh an do
		// check benh nao thuoc bac si hien tai
		// model add benh anh
		// model add ds benh cua benh an do ( tru benh cua bac si hien tai kham)
		// model add ("bs-benh") benh cua bac si hien tai kham ( gen ra nut xac nhan
		// khoi )

		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);

			// fake benh
			Optional<Benh> benh1 = benhService.getById(benhAn.getIdBenh());
			List<Benh> dsBenh = Arrays.asList(benh1.get());

			// model add benh cua benh an
			model.addAttribute("dsBenh", dsBenh);


			return "Bacsi/ViewBenhAn";
		}
		return "redirect:/404";
	}

	@PostMapping("/bacsi/xembenhan/{id}")
	public String handleUpdateKhoiBenh(@PathVariable int id, @RequestBody(required = false) String json) {
		if (json != null) {
			Optional<BenhAn> benhAn = benhAnService.getById(id);
			benhAn.get().setDaKhoi(true);
			benhAn.get().setDelete(true);
			benhAnService.saveBenhAn(benhAn.get());

		}
		return "redirect:/bacsi/list-benhan";
	}

	@GetMapping(value = { "/bacsi/xemdonthuoc/{id}" })
	public String showBSDonThuoc(@PathVariable int id, Model model) {

		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);
			List<String> donthuoc = Arrays.asList(benhAn.getDsDonThuoc().split(" "));
			List<DonThuoc> listDonThuoc = new LinkedList<>();
			for (String s: donthuoc
				 ) {
				int idDonThuoc = Integer.parseInt(s);
				DonThuoc donThuoc = donThuocService.getByIdDelete(idDonThuoc);
				listDonThuoc.add(donThuoc);
			}
			model.addAttribute("donThuoc", listDonThuoc);

			return "Bacsi/ViewDonThuoc";
		}
		return "redirect:/404";
	}
	@GetMapping("/bacsi/luong")
	public String tinhLuongBacSy(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date sdate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date edate,Model model){

		List<BacSyLuong> listLuong = bacSyService.tinhLuongBacSy(sdate, edate);
		System.err.println("55555555555555555555555555555");
		model.addAttribute("listLuong", listLuong);
		System.err.println("4444444444444444444444444444444");
//		System.out.println(listLuong);
		return "QuanLyNhanSu/Salary";
	}
}
