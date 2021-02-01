package br.com.stoom.adapter.inbound.rest.controller;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stoom.adapter.inbound.rest.bo.AddressRestBO;
import br.com.stoom.adapter.inbound.rest.errors.BadRequestNotFoundRegisterToChangeException;
import br.com.stoom.adapter.inbound.rest.mapper.InboundRestMapper;
import br.com.stoom.adapter.inbound.rest.request.json.CreateAddressRequestJson;
import br.com.stoom.adapter.inbound.rest.request.json.PatchAddressRequestJson;
import br.com.stoom.adapter.inbound.rest.request.json.UpdateAddressRequestJson;
import br.com.stoom.adapter.inbound.rest.response.json.GetAddressResponseJson;
import br.com.stoom.core.errors.NotFoundRegisterToChangerValuesException;
import br.com.stoom.core.port.inbound.AddressCrudDomainPortInbound;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Api
@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressCrudDomainInboundController {

	private AddressCrudDomainPortInbound addressCrudDomainPortInbound;

	private InboundRestMapper mapper;

	private Environment env;

	private ServletContext servletContext;

	private final AddressRestBO addressRestBO = AddressRestBO.getInstance();

	@PostMapping
	public ResponseEntity<URI> create(@Valid @RequestBody CreateAddressRequestJson body, HttpServletRequest request) {

		var coreDTO = mapper.from(body);

		var addressId = addressCrudDomainPortInbound.create(coreDTO);

		var applicationUri = addressRestBO.getUriCreate(env, servletContext.getContextPath(), request, addressId);

		return ResponseEntity.created(applicationUri).build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<GetAddressResponseJson> read(@Valid @NotNull @PathVariable("id") String addressId) {
		var optAddress = addressCrudDomainPortInbound.read(addressId);

		if (optAddress.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var bodyResponse = optAddress.map(mapper::from).orElseThrow();

		return ResponseEntity.ok(bodyResponse);

	}

	@GetMapping()
	public ResponseEntity<Page<GetAddressResponseJson>> read(Pageable pageable) {
		var pageAddress = addressCrudDomainPortInbound.read(pageable);

		if (pageAddress.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var bodyResponse = pageAddress.map(mapper::from);

		return ResponseEntity.ok(bodyResponse);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody UpdateAddressRequestJson body,
			@Valid @NotNull @PathVariable("id") String addressId) {

		var coreDTO = mapper.from(body);

		coreDTO.setId(addressId);

		try {
			addressCrudDomainPortInbound.update(coreDTO);
		} catch (NotFoundRegisterToChangerValuesException e) {
			throw new BadRequestNotFoundRegisterToChangeException();
		}

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> patch(@RequestBody PatchAddressRequestJson body,
			@Valid @NotNull @PathVariable("id") String addressId) {

		var coreDTO = mapper.from(body);

		coreDTO.setId(addressId);
		try {
			addressCrudDomainPortInbound.patch(coreDTO);
		} catch (NotFoundRegisterToChangerValuesException e) {
			throw new BadRequestNotFoundRegisterToChangeException();
		}

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") String addressId) {

		try {
			addressCrudDomainPortInbound.delete(addressId);
		} catch (NotFoundRegisterToChangerValuesException e) {
			throw new BadRequestNotFoundRegisterToChangeException();
		}

		return ResponseEntity.noContent().build();
	}

}
