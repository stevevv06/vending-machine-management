package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.domain.Item;
import com.avasquez.repository.ItemRepository;
import com.avasquez.vendingadmin.service.dto.ItemCriteria;
import com.avasquez.vendingadmin.service.dto.ItemDTO;
import com.avasquez.vendingadmin.service.mapper.ItemMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link Item} entities in the database.
 * The main input is a {@link ItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ItemDTO} or a {@link Page} of {@link ItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ItemQueryService extends QueryService<Item> {

    private final Logger log = LoggerFactory.getLogger(ItemQueryService.class);

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemQueryService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * Return a {@link List} of {@link ItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ItemDTO> findByCriteria(ItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Item> specification = createSpecification(criteria);
        return itemMapper.toDto(itemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> findByCriteria(ItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Item> specification = createSpecification(criteria);
        return itemRepository.findAll(specification, page)
            .map(itemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Item> specification = createSpecification(criteria);
        return itemRepository.count(specification);
    }

    /**
     * Function to convert {@link ItemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Item> createSpecification(ItemCriteria criteria) {
        Specification<Item> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Item_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Item_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Item_.code));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Item_.price));
            }
            if (criteria.getVendingMachineItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getVendingMachineItemId(),
                    root -> root.join(Item_.vendingMachineItems, JoinType.LEFT).get(VendingMachineItem_.id)));
            }
            if (criteria.getVendingMachineTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getVendingMachineTransactionId(),
                    root -> root.join(Item_.vendingMachineTransactions, JoinType.LEFT).get(VendingMachineTransaction_.id)));
            }
        }
        return specification;
    }
}
