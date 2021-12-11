package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
public class Thuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Không được để trống")
    private String ten;
    private Float gia;
    @OneToMany(targetEntity = DonThuoc.class, mappedBy = "thuoc")
    private Set<DonThuoc> donThuoc;
    private boolean isDelete = false;
}
