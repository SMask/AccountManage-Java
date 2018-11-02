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

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.username=:qUsername, us.password=:qPassword, us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName,us.birthday=:qBirthday,us.updateTime=:qUpdateTime where us.id=:qId")
    void update(@Param("qUsername") String username, @Param("qPassword") String password, @Param("qNickname") String nickname, @Param("qFirstName") String firstName, @Param("qLastName") String lastName, @Param("qBirthday") long birthday, @Param("qUpdateTime") long updateTime, @Param("qId") Integer id);
}
