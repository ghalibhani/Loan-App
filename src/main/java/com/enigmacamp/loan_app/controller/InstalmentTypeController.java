package com.enigmacamp.loan_app.controller;

import com.enigmacamp.loan_app.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.loan_app.dto.response.CommonResponse;
import com.enigmacamp.loan_app.dto.response.InstalmentTypeResponse;
import com.enigmacamp.loan_app.entity.InstalmentType;
import com.enigmacamp.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instalment-types")
@RequiredArgsConstructor
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    public ResponseEntity<?> createInstalmentType(@RequestBody InstalmentTypeRequest request) {
        InstalmentTypeResponse instalmentTypeRequest = instalmentTypeService.createInstalmentType(request);
        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully created new instalment type")
                .statusCode(201)
                .data(instalmentTypeRequest)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstalmentTypeById(@PathVariable String id) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.getInstalmentTypeById(id);
        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully get instalment type by id")
                .statusCode(200)
                .data(instalmentTypeResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }

   @GetMapping
    public ResponseEntity<?> getAllInstalmentType() {
        List<InstalmentType> instalmentTypeList = instalmentTypeService.getAllInstalmentType();
        CommonResponse<List<InstalmentType>> commonResponse = CommonResponse.<List<InstalmentType>>builder()
                .message("Successfully get all instalment type")
                .statusCode(200)
                .data(instalmentTypeList)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }

   @PutMapping
    public ResponseEntity<?> updateInstalmentType(@RequestBody UpdateInstalmentTypeRequest request) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.updateInstalmentType(request);
        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully update instalment type")
                .statusCode(200)
                .data(instalmentTypeResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstalmentTypeById(@PathVariable String id) {
        instalmentTypeService.deleteInstalmentTypeById(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .message("Successfully delete instalment type")
                .statusCode(200)
                .data("Successfully delete instalment type")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
   }
}
