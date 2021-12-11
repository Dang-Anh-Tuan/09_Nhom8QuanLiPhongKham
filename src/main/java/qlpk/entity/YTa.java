package qlpk.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class YTa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "không được để trống")
    private String ten;
    @NotEmpty(message = "Không được để trống")
    private String cmt;
    @NotEmpty(message = "Không được để trống")
    private String trinhDo;
    private int thamNien;
    @NotEmpty(message = "Không được để trống")
    private String diaChi;
    @NotEmpty(message = "Không được để trống")
    private String sdt;
    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Past(message = "Ngày sinh phải trước ngày hiện tại")
    private Date bd;
    @OneToMany(targetEntity = BenhAn.class, mappedBy = "yTa")
    private Set<BenhAn> benhAn;
    @OneToMany(targetEntity = ThongTinChamSoc.class, mappedBy = "yTa")
    private Set<ThongTinChamSoc> thongTinChamSoc;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName")
    private User user;
    private boolean isDelete = false;
}
