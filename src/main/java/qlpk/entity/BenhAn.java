package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Entity
public class BenhAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKham;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayVaoVien;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayXuatVien;
    private float tongTien;
    private boolean daPhat = true;
    private boolean daKhoi;
    private String dsDonThuoc;
    @ManyToOne(targetEntity = BacSy.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "bac_sy_id", referencedColumnName = "id")
    @Nullable
    private BacSy bacSy;
    @ManyToOne(targetEntity = BenhNhan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "benh_nhan_id", referencedColumnName = "id")
    private BenhNhan benhNhan;
    @ManyToOne(targetEntity = YTa.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "y_ta_id", referencedColumnName = "id")
    private YTa yTa;
    private boolean isDelete = false;
    private Integer idBenh;
}
