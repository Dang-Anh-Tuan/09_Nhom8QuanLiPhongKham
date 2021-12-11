package qlpk.modelUtil;

import java.util.List;

import lombok.Data;
import qlpk.entity.Benh;

@Data
public class BenhDanhSachBenh {
	private Integer idBenh;
	private List<Benh> dsBenh;
}
