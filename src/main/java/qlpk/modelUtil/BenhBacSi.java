package qlpk.modelUtil;

import java.util.List;

import lombok.Data;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;

@Data
public class BenhBacSi {
	private List<Benh> dsBenh;
	private List<Benh> dsBenhChon;

}
