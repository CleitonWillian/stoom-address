package br.com.stoom.adapter.outbound.rest.geolocate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stoom.adapter.outbound.rest.geolocate.response.json.GoocleGeolocateResponseJson;

@FeignClient(value = "GoogleClient", url = "${geolocate.url}")
public interface GoogleClient {

	@GetMapping
	public ResponseEntity<GoocleGeolocateResponseJson> findGeolocate(@RequestParam("address") String address,  @RequestParam("key") String key ); 
	
}
