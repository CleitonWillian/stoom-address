package br.com.stoom.adapter.outbound.nosql.address.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Address")
@Getter
@Setter
public class AddressModel {

	@Id
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
