package qlpk.entity;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
public class Benh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Không được để trống")
    private String tenBenh;
    @NotEmpty(message = "Không được để trống")
    private String mota;
    @ManyToOne(targetEntity = BacSy.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "bac_sy_id", referencedColumnName = "id")
    private BacSy bacSy;
    @OneToMany(targetEntity = DonThuoc.class, mappedBy = "benh")
    private Set<DonThuoc> donThuoc;
    private boolean isDelete = false;
}
