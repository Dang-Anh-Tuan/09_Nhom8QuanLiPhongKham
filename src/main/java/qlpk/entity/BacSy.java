package qlpk.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class BacSy  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Tên không được để trống")
    private String ten;
    @NotEmpty(message = "Không được để trống")
    private String cmt;
    @NotEmpty(message = "Không được để trống")
    private String diaChi;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date ngaySinh;
    @NotEmpty(message = "Không được để trống")
    private String bacNghe;
    private int thamNien;
    @NotEmpty(message = "Không được để trống")
    private String trinhDo;
    @NotEmpty(message = "Không được để trống")
    private String chuyenMon;
    private String sdt;
    @OneToMany(targetEntity = Benh.class, mappedBy = "bacSy")
    private Set<Benh> benh;
    @OneToMany(targetEntity = BenhAn.class, mappedBy = "bacSy")
    private Set<BenhAn> benhAn;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName")
    private User user;
    private boolean isDelete = false;
}
