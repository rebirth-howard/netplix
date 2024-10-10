package com.hw.netplix.repository.user.social;

import com.hw.netplix.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialUserJpaRepository extends JpaRepository<SocialUserEntity, String> {
    Optional<SocialUserEntity> findByProviderId(String providerId);

}
