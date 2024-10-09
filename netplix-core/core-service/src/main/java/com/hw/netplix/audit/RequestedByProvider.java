package com.hw.netplix.audit;

import java.util.Optional;

public interface RequestedByProvider {
    Optional<String> getRequestedBy();
}
