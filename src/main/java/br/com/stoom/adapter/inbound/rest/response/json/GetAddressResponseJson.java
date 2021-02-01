package br.com.stoom.adapter.inbound.rest.response.json;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAddressResponseJson {

	private String id;
	private String streetName;
	private Integer number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private BigDecimal latitude;
	private BigDecimal longitude;

}
