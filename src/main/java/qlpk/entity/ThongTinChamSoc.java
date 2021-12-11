package qlpk.entity;

import lombok.Data;
import org.springframework.lang.Nullable;
import qlpk.entity.enums.HinhThuc;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class ThongTinChamSoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private HinhThuc hinhThuc;
    @ManyToOne(targetEntity = YTa.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "y_ta_id", referencedColumnName = "id")
    private YTa yTa;
    @ManyToOne(targetEntity = BenhNhan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "benh_nhan_id", referencedColumnName = "id")
    private BenhNhan benhNhan;
    @OneToMany(targetEntity = DonThuoc.class, mappedBy = "thongTinChamSoc")
    @Nullable
    private Set<DonThuoc> donThuoc;
}
