package com.sitesquad.ministore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayslipDTO implements Serializable {
    private Long payslipId;
    private Long userId;
    private String name;
    private String roleName;
    private String email;
    private String startDate;
    private String endDate;
    private Integer shiftCount;
    private Double salary;
    private Integer totalHour;
    private String date;
    private Boolean isPaid;
}
