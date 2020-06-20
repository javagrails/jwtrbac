package com.jwtrbac.app.service;

import com.jwtrbac.app.domain.UserRM;
import com.jwtrbac.app.repository.UserRMRepository;
import com.jwtrbac.app.service.dto.UserRMDTO;
import com.jwtrbac.app.service.mapper.UserRMMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.jwtrbac.app.domain.UserRM}.
 */
@Service
@Transactional
public class UserRMService {

    private final Logger log = LoggerFactory.getLogger(UserRMService.class);

    private final UserRMRepository userRMRepository;

    private final UserRMMapper userRMMapper;

    public UserRMService(UserRMRepository userRMRepository, UserRMMapper userRMMapper) {
        this.userRMRepository = userRMRepository;
        this.userRMMapper = userRMMapper;
    }

    /**
     * Save a userRM.
     *
     * @param userRMDTO the entity to save.
     * @return the persisted entity.
     */
    public UserRMDTO save(UserRMDTO userRMDTO) {
        log.debug("Request to save UserRM : {}", userRMDTO);
        UserRM userRM = userRMMapper.toEntity(userRMDTO);
        userRM = userRMRepository.save(userRM);
        return userRMMapper.toDto(userRM);
    }

    /**
     * Get all the userRMs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UserRMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserRMs");
        return userRMRepository.findAll(pageable)
            .map(userRMMapper::toDto);
    }


    /**
     * Get one userRM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserRMDTO> findOne(Long id) {
        log.debug("Request to get UserRM : {}", id);
        return userRMRepository.findById(id)
            .map(userRMMapper::toDto);
    }

    /**
     * Delete the userRM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserRM : {}", id);
        userRMRepository.deleteById(id);
    }
}
