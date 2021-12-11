package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lieuLuong;
    private float tongTien;
    private Date ngayCap;
    private String cacDung;
    @ManyToOne(targetEntity = Benh.class)
    @JoinColumn(name = "benh_id")
    private Benh benh;
    @ManyToOne(targetEntity = Thuoc.class)
    @JoinColumn(name = "thuoc_id")
    private Thuoc thuoc;
    @ManyToOne(targetEntity = ThongTinChamSoc.class)
    private ThongTinChamSoc thongTinChamSoc;
    private boolean isDelete = false;
}
