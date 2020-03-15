package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
}
