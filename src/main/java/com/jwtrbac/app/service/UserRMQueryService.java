package com.jwtrbac.app.service;

import com.jwtrbac.app.domain.UserRM;
import com.jwtrbac.app.domain.UserRM_;
import com.jwtrbac.app.repository.UserRMRepository;
import com.jwtrbac.app.service.dto.UserRMCriteria;
import com.jwtrbac.app.service.dto.UserRMDTO;
import com.jwtrbac.app.service.mapper.UserRMMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for {@link UserRM} entities in the database.
 * The main input is a {@link UserRMCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserRMDTO} or a {@link Page} of {@link UserRMDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserRMQueryService extends QueryService<UserRM> {

    private final Logger log = LoggerFactory.getLogger(UserRMQueryService.class);

    private final UserRMRepository userRMRepository;

    private final UserRMMapper userRMMapper;

    public UserRMQueryService(UserRMRepository userRMRepository, UserRMMapper userRMMapper) {
        this.userRMRepository = userRMRepository;
        this.userRMMapper = userRMMapper;
    }

    /**
     * Return a {@link List} of {@link UserRMDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserRMDTO> findByCriteria(UserRMCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UserRM> specification = createSpecification(criteria);
        return userRMMapper.toDto(userRMRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserRMDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserRMDTO> findByCriteria(UserRMCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UserRM> specification = createSpecification(criteria);
        return userRMRepository.findAll(specification, page)
            .map(userRMMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UserRMCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UserRM> specification = createSpecification(criteria);
        return userRMRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<UserRM> createSpecification(UserRMCriteria criteria) {
        Specification<UserRM> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserRM_.id));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(), UserRM_.userId));
            }
            if (criteria.getServiceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceId(), UserRM_.serviceId));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), UserRM_.url));
            }
            if (criteria.getHttpMethod() != null) {
                specification = specification.and(buildSpecification(criteria.getHttpMethod(), UserRM_.httpMethod));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), UserRM_.active));
            }
            if (criteria.getCombine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCombine(), UserRM_.combine));
            }
            if (criteria.getCombineHash() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCombineHash(), UserRM_.combineHash));
            }
        }
        return specification;
    }
}
