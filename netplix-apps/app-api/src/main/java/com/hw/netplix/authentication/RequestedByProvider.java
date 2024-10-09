package com.hw.netplix.authentication;

import java.util.Optional;

public interface RequestedByProvider {
    Optional<String> getRequestedBy();
}
