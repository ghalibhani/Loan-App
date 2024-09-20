package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.response.InstalmentTypeResponse;
import com.enigmacamp.loan_app.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request);
    InstalmentTypeResponse getInstalmentTypeById(String id);
    InstalmentTypeResponse updateInstalmentType(UpdateInstalmentTypeRequest request);
    List<InstalmentType> getAllInstalmentType();
    void deleteInstalmentTypeById(String id);
}
