package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.Item;
import com.avasquez.vendingadmin.repository.ItemRepository;
import com.avasquez.vendingadmin.service.api.ItemService;
import com.avasquez.vendingadmin.service.dto.ItemDTO;
import com.avasquez.vendingadmin.service.mapper.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Item}.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * Save a item.
     *
     * @param itemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        log.debug("Request to save Item : {}", itemDTO);
        Item item = itemMapper.toEntity(itemDTO);
        item = itemRepository.save(item);
        return itemMapper.toDto(item);
    }

    /**
     * Save multiple items.
     *
     * @param itemDTOs the entity to save.
     * @return the persisted entity.
     */
    public List<ItemDTO> save(List<ItemDTO> itemDTOs) {
        log.debug("Request to save Items");
        List<Item> items = itemMapper.toEntity(itemDTOs);
        items = itemRepository.saveAll(items);
        return itemMapper.toDto(items);
    }

    /**
     * Get all the items.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Items");
        return itemRepository.findAll(pageable)
            .map(itemMapper::toDto);
    }


    /**
     * Get one item by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> find(Long id) {
        log.debug("Request to get Item : {}", id);
        return itemRepository.findById(id)
            .map(itemMapper::toDto);
    }

    /**
     * Delete the item by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Item : {}", id);
        itemRepository.deleteById(id);
    }
}
