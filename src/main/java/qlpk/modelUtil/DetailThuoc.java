package qlpk.modelUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailThuoc {
	@JsonProperty(value = "idThuoc")
	private int idThuoc;
	@JsonProperty(value = "lieuDung")
	private int lieuDung;
	@JsonProperty(value = "cachDung")
	private String cachDung;

}