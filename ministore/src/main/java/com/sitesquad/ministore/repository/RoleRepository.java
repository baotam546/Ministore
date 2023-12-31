/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitesquad.ministore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.sitesquad.ministore.model.Role;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>{
    Role findByNameIgnoreCaseAndIsDeletedIsFalse(String name);
    Role findByRoleIdAndIsDeletedFalse(Long id);
    List<Role> findAllByIsDeletedFalse();
}
