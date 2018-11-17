package com.mask.repository;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

    /**
     * 根据 UserEntity 查找集合
     *
     * @param userEntity userEntity
     * @return List
     */
    List<BlogEntity> findAllByUserByUserId(UserEntity userEntity);

    /**
     * 修改博文信息
     *
     * @param title      title
     * @param userId     userId
     * @param content    content
     * @param updateTime updateTime
     * @param id         id
     */
    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.title=:qTitle, blog.userByUserId.id=:qUserId, blog.content=:qContent, blog.updateTime=:qUpdateTime where blog.id=:qId")
    void update(@Param("qTitle") String title, @Param("qUserId") int userId, @Param("qContent") String content, @Param("qUpdateTime") long updateTime, @Param("qId") int id);
}

