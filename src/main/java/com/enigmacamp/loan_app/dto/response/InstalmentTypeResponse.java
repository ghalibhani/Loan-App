package com.enigmacamp.loan_app.dto.response;

import com.enigmacamp.loan_app.constant.EInstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeResponse {
    private String id;
    private EInstalmentType instalmentType;
}
