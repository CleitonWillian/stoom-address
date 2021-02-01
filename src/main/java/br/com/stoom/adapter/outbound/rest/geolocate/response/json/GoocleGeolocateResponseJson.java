package br.com.stoom.adapter.outbound.rest.geolocate.response.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoocleGeolocateResponseJson {

	private List<ResultResponseJson> results;
	
}
