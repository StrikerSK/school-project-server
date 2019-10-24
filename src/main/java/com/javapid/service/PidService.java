package com.javapid.service;

import com.javapid.entity.CouponEntity;
import com.javapid.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.javapid.service.FileParser.getEmployeeFromJson;

@Service
public class PidService {

    private final PidCouponsRepository pidCouponsRepository;

    public PidService(PidCouponsRepository pidCouponsRepository) {
        this.pidCouponsRepository = pidCouponsRepository;
    }

    public void saveData(CouponEntity data) {
        pidCouponsRepository.save(data);
    }

    public void saveDataFromFile(MultipartFile file) throws IOException {
        CouponEntity data = getEmployeeFromJson(file);
        saveData(data);
    }

    public List<CouponEntity> getAllData() {
        return pidCouponsRepository.findAll();
    }

    public List<CouponEntity> getDataByCode(String code) {
        return pidCouponsRepository.getByCode(code);
    }
}
