package com.integrator.equiWeb.repository;


import com.integrator.equiWeb.dto.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.integrator.equiWeb.repository.Query.INSERT_INTO_ACCESS_LOG;


@RequiredArgsConstructor
@Repository
public class ActivityRepository implements ActivityOps{
    private final JdbcTemplate jdbcTemplate;
     String userAccessCode = "proxy_user_role";
    String channelCode = "ATM";
    int channelId = 7;
    @Override
    public boolean saveRequest(ServiceResponse serviceResponse) {
        System.out.println(serviceResponse);
        int result = jdbcTemplate.update(INSERT_INTO_ACCESS_LOG,
            "EXTUSER", "EXTUSER",  "A", 1, serviceResponse.getFromAccount(), serviceResponse.getToAccount(),
            serviceResponse.getTranAmount1(), serviceResponse.getIsoCurrency(),
            serviceResponse.getCardNo(), serviceResponse.getEffectiveDt(), "CREATE",
            serviceResponse.getOrigReferenceNo(), serviceResponse.getReferenceNo(),
            serviceResponse.isReversal(),
            serviceResponse.getATMSwitchID(), serviceResponse.getTerminalId(),
            userAccessCode, channelCode,
            channelId, serviceResponse.getServiceId(),
            serviceResponse.getTranAmount2(),
            serviceResponse.getProcessingCode(), serviceResponse.getResponseCode());
        return result == 0 ? true : false;

    }
}
