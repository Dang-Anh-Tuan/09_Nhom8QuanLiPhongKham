package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class BenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Không được để trống")
    private String ten;
    @NotEmpty(message = "Không được để trống")
    private String cmt;
    @NotEmpty(message = "Không được để trống")
    private String diaChi;
    @NotEmpty(message = "Không được để trống")
    private String sdt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;
}
