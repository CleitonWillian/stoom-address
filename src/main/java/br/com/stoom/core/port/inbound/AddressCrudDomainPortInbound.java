package br.com.stoom.core.port.inbound;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.errors.NotFoundRegisterToChangerValuesException;

public interface AddressCrudDomainPortInbound {

	public String create(AddressCoreDTO addressCoreDTO);
	public Optional<AddressCoreDTO> read(String id);
	public Page<AddressCoreDTO> read(Pageable pageable);
	public void update(AddressCoreDTO addressCoreDTO) throws NotFoundRegisterToChangerValuesException;
	public void patch(AddressCoreDTO addressCoreDTO) throws NotFoundRegisterToChangerValuesException;
	public void delete(String id) throws NotFoundRegisterToChangerValuesException;
	
}
