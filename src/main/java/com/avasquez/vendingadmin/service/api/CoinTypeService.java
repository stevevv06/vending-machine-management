package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.CoinTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.CoinType}.
 */
public interface CoinTypeService extends CrudService<CoinTypeDTO, Long>  {
}
