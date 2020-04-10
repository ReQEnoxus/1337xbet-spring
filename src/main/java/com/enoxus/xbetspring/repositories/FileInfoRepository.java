package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findFirstByStorageFileName(String storageFileName);
}
