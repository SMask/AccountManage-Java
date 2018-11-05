package com.mask.repository;

import com.mask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 根据Username查找用户集合
     *
     * @param username username
     * @return List<UserEntity>
     */
    List<UserEntity> findAllByUsername(String username);

    /**
     * 修改用户信息
     *
     * @param username   username
     * @param password   password
     * @param nickname   nickname
     * @param firstName  firstName
     * @param lastName   lastName
     * @param birthday   birthday
     * @param updateTime updateTime
     * @param id         id
     */
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity user set user.username=:qUsername, user.password=:qPassword, user.nickname=:qNickname, user.firstName=:qFirstName, user.lastName=:qLastName,user.birthday=:qBirthday,user.updateTime=:qUpdateTime where user.id=:qId")
    void update(@Param("qUsername") String username, @Param("qPassword") String password, @Param("qNickname") String nickname, @Param("qFirstName") String firstName, @Param("qLastName") String lastName, @Param("qBirthday") long birthday, @Param("qUpdateTime") long updateTime, @Param("qId") int id);
}
