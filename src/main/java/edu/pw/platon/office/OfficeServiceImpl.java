package edu.pw.platon.office;

import edu.pw.platon.utilities.CustomProperties;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {

    private CustomProperties customProperties;

    public OfficeServiceImpl(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Override
    public void openEnrollments() {
        customProperties.setEnrollments("open");
    }

    @Override
    public void closeEnrollments() {
        customProperties.setEnrollments("closed");
    }
}
