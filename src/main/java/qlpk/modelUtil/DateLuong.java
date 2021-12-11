package qlpk.modelUtil;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DateLuong {
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date ngayBD;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date ngayKT;
}
