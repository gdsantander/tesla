package com.gsantander.tesla.repositories;

import java.util.List;
import java.util.Optional;

import com.gsantander.tesla.model.TslProfile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository <TslUser, Integer> {

    Optional<TslUser> findByCredentialsUserName(String credentialsUserName);

    List<TslUser> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndLastNameAndFirstNameAndEmail(Integer idCompany, String lastName, String firstName, String email);

    boolean existsByIdCompanyAndLastNameAndFirstNameAndEmailAndIdUserIsNot(Integer idCompany, String lastName, String firstName, String email, Integer idUser);

    @Modifying
    @Query(value = "update USERS set CREDENTIALS_PASSWORD = :credentialsPassword where IDUSER = :idUser", nativeQuery = true)
    void passwordChange(@Param("idUser") int idUser, @Param("credentialsPassword") String credentialsPassword);

}
