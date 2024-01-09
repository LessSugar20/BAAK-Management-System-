package com.example.baakKel17.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baakKel17.model.Surat;

public interface SuratRepository extends JpaRepository<Surat, Long> {
	List<Surat> findByUserId(Long userId);
}
