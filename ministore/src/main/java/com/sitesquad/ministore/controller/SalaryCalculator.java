package com.sitesquad.ministore.controller;

import com.sitesquad.ministore.constant.SystemConstant;
import com.sitesquad.ministore.model.Payslip;
import com.sitesquad.ministore.dto.ResponseObject;
import com.sitesquad.ministore.model.User;
import com.sitesquad.ministore.model.UserShift;
import com.sitesquad.ministore.service.PayslipService;
import com.sitesquad.ministore.service.UserService;
import com.sitesquad.ministore.service.shift.UserShiftService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class SalaryCalculator {

    @Autowired
    PayslipService payslipService;

    @Autowired
    UserShiftService userShiftService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 0 30 * *")
    @GetMapping("/salary")
    public ResponseEntity<ResponseObject> calculateSalary() {
        List<User> userList = userService.findAllExceptAdmin();


        for (User user : userList) {

            List<UserShift> userShiftList = userShiftService.findAllByIsPaidAndUserId(user.getUserId());
//            System.out.println(userShiftList);
            if (userShiftList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(404, "Not Found UserShift", "")
                );
            }

            Payslip payslip = new Payslip();


            payslip.setUserId(userShiftList.get(0).getUserId());
            Integer shiftCount = new Integer(0);
            Double salary = new Double(0.0);
            Integer totalHour = new Integer(0);

            for (UserShift userShift : userShiftList) {
                Long workingPeriod = ChronoUnit.HOURS.between(userShift.getStartTime(), userShift.getEndTime());
                Double salaryInADay = userShift.getUser().getRole().getBaseSalary() * workingPeriod * userShift.getShift().getCoefficient();
                if (userShift.getIsWeekend() == true) { // weekend
                    salaryInADay *= 2; // using coeffience const
                } else { // not weekend
                    salaryInADay *= 1.5; // using coeffience const
                }
                if (userShift.getIsHoliday() == true) { // holiday
                    salaryInADay *= 3; // using coeffience const
                }
                salary += salaryInADay;
                shiftCount++;
                totalHour += workingPeriod.intValue();
                userShift.setIsPaid(true);
                userShiftService.edit(userShift);
            }
            payslip.setShiftCount(shiftCount);

            payslip.setStartDate(Date.from(userShiftList.get(0).getStartTime().toInstant()));
            payslip.setEndDate(Date.from(userShiftList.get(userShiftList.size() - 1).getEndTime().toInstant()));

            payslip.setSalary(salary);
            payslip.setTotalHours(totalHour);
            payslipService.add(payslip);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Successfull", "")
        );
    }

    @GetMapping("/salary/pay")
    public ResponseEntity<ResponseObject> paySalary(@RequestBody Long payslipId) {
        Payslip foundPayslip = payslipService.findById(payslipId);
        if(foundPayslip != null) {
            if(foundPayslip.getIsPaid() == null || foundPayslip.getIsPaid() == false) {
                foundPayslip.setDate(Date.from(SystemConstant.LOCAL_DATE_TIME_NOW.atZone(ZoneId.systemDefault()).toInstant()));
                foundPayslip.setIsPaid(true);
                payslipService.edit(foundPayslip);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Successfull", foundPayslip)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(404, "Not found payslip", "")
        );
    }
}
