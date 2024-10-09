package com.hw.netplix.controller.sample;

import com.hw.netplix.sample.SearchSampleUseCase;
import com.hw.netplix.sample.response.SampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SearchSampleUseCase searchSampleUseCase;

    @GetMapping("/api/v1/sample")
    public SampleResponse getSample() {
        return searchSampleUseCase.getSample();
    }
}

