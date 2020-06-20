package com.jwtrbac.app.web.rest;

import com.jwtrbac.app.service.UserRMQueryService;
import com.jwtrbac.app.service.UserRMService;
import com.jwtrbac.app.service.dto.UserRMCriteria;
import com.jwtrbac.app.service.dto.UserRMDTO;
import com.jwtrbac.app.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jwtrbac.app.domain.UserRM}.
 */
@RestController
@RequestMapping("/api")
public class UserRMResource {

    private final Logger log = LoggerFactory.getLogger(UserRMResource.class);

    private static final String ENTITY_NAME = "userRM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserRMService userRMService;

    private final UserRMQueryService userRMQueryService;

    public UserRMResource(UserRMService userRMService, UserRMQueryService userRMQueryService) {
        this.userRMService = userRMService;
        this.userRMQueryService = userRMQueryService;
    }

    /**
     * {@code POST  /user-rms} : Create a new userRM.
     *
     * @param userRMDTO the userRMDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userRMDTO, or with status {@code 400 (Bad Request)} if the config has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-rms")
    public ResponseEntity<UserRMDTO> createUserRM(@Valid @RequestBody UserRMDTO userRMDTO) throws URISyntaxException {
        log.debug("REST request to save UserRM : {}", userRMDTO);
        if (userRMDTO.getId() != null) {
            throw new BadRequestAlertException("A new userRM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserRMDTO result = userRMService.save(userRMDTO);
        return ResponseEntity.created(new URI("/api/user-rms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-rms} : Updates an existing userRM.
     *
     * @param userRMDTO the userRMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userRMDTO,
     * or with status {@code 400 (Bad Request)} if the userRMDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userRMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-rms")
    public ResponseEntity<UserRMDTO> updateUserRM(@Valid @RequestBody UserRMDTO userRMDTO) throws URISyntaxException {
        log.debug("REST request to update UserRM : {}", userRMDTO);
        if (userRMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserRMDTO result = userRMService.save(userRMDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userRMDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-rms} : get all the userRMs.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @param criteria    the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userRMs in body.
     */
    @GetMapping("/user-rms")
    public ResponseEntity<List<UserRMDTO>> getAllUserRMs(UserRMCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get UserRMs by criteria: {}", criteria);
        Page<UserRMDTO> page    = userRMQueryService.findByCriteria(criteria, pageable);
        HttpHeaders     headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-rms/count} : count all the userRMs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/user-rms/count")
    public ResponseEntity<Long> countUserRMs(UserRMCriteria criteria) {
        log.debug("REST request to count UserRMs by criteria: {}", criteria);
        return ResponseEntity.ok().body(userRMQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /user-rms/:id} : get the "id" userRM.
     *
     * @param id the id of the userRMDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userRMDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-rms/{id}")
    public ResponseEntity<UserRMDTO> getUserRM(@PathVariable Long id) {
        log.debug("REST request to get UserRM : {}", id);
        Optional<UserRMDTO> userRMDTO = userRMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userRMDTO);
    }

    /**
     * {@code DELETE  /user-rms/:id} : delete the "id" userRM.
     *
     * @param id the id of the userRMDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-rms/{id}")
    public ResponseEntity<Void> deleteUserRM(@PathVariable Long id) {
        log.debug("REST request to delete UserRM : {}", id);
        userRMService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
