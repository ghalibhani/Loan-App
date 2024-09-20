package com.enigmacamp.loan_app.dto.request;

import com.enigmacamp.loan_app.constant.EInstalmentType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateInstalmentTypeRequest {
    private String id;
    private EInstalmentType instalmentType;
}
