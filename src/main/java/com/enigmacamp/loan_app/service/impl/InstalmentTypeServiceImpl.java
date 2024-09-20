package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.response.InstalmentTypeResponse;
import com.enigmacamp.loan_app.entity.InstalmentType;
import com.enigmacamp.loan_app.repository.InstalmentTypeRepository;
import com.enigmacamp.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {

    private final InstalmentTypeRepository instalmentTypeRepository;
    @Override
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request) {
        try {
            InstalmentType instalmentType = (InstalmentType.builder()
                    .instalmentType(request.getInstalmentType())
                    .build());
            instalmentTypeRepository.saveAndFlush(instalmentType);
            return convertToInstalmentTypeResponse(instalmentType);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "test");
        }
    }

    @Override
    public InstalmentTypeResponse getInstalmentTypeById(String id) {
        InstalmentType instalmentType = findByIdOrThrowNotFound(id);
        return convertToInstalmentTypeResponse(instalmentType);
    }

    @Override
    public InstalmentTypeResponse updateInstalmentType(UpdateInstalmentTypeRequest request) {
        try {
            InstalmentType instalmentType = findByIdOrThrowNotFound(request.getId());
            instalmentType.setInstalmentType(request.getInstalmentType());
            instalmentTypeRepository.saveAndFlush(instalmentType);
            return convertToInstalmentTypeResponse(instalmentType);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "test");
        }
    }

    @Override
    public List<InstalmentType> getAllInstalmentType() {
        return instalmentTypeRepository.findAll();
    }

    @Override
    public void deleteInstalmentTypeById(String id) {
        InstalmentType instalmentType = findByIdOrThrowNotFound(id);
        instalmentTypeRepository.delete(instalmentType);

    }

    private InstalmentType findByIdOrThrowNotFound(String id){
        return instalmentTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instalment type not found"));
    }

    private InstalmentTypeResponse convertToInstalmentTypeResponse(InstalmentType instalmentType){
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }
}
