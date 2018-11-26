package com.mask.utils;

import com.mask.model.BlogEntity;
import com.mask.model.UserEntity;
import com.mask.repository.BlogRepository;
import com.mask.repository.UserRepository;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Model 帮助类
 */
public class ModelHelper {

    /**
     * 查找User
     *
     * @param userRepository userRepository
     * @param userId         userId
     * @return UserEntity
     */
    public static UserEntity findById(UserRepository userRepository, int userId) {
        UserEntity userEntity = null;
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userEntity = userOptional.get();
        }
        return userEntity;
    }

    /**
     * 查找Blog
     *
     * @param blogRepository blogRepository
     * @param blogId         blogId
     * @return BlogEntity
     */
    public static BlogEntity findById(BlogRepository blogRepository, int blogId) {
        BlogEntity blogEntity = null;
        Optional<BlogEntity> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            blogEntity = blogOptional.get();
        }
        return blogEntity;
    }

    /**
     * 获取 User JSONObject
     *
     * @param userEntity userEntity
     * @return JSONObject
     */
    public static JSONObject getJSONObject(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        JSONObject temp = new JSONObject();

        temp.put("id", userEntity.getId());
        temp.put("username", userEntity.getUsername());
        temp.put("password", userEntity.getPassword());
        temp.put("nickname", userEntity.getNickname());
        temp.put("firstName", userEntity.getFirstName());
        temp.put("lastName", userEntity.getLastName());
        temp.put("birthday", TimeUtils.getDate(userEntity.getBirthday()));
        temp.put("registerTime", TimeUtils.getDateTime(userEntity.getRegisterTime()));
        temp.put("updateTime", TimeUtils.getDateTime(userEntity.getUpdateTime()));
        temp.put("token", userEntity.getToken());

        return temp;
    }

    /**
     * 获取 Blog JSONObject
     *
     * @param blogEntity blogEntity
     * @return JSONObject
     */
    public static JSONObject getJSONObject(BlogEntity blogEntity) {
        if (blogEntity == null) {
            return null;
        }
        JSONObject temp = new JSONObject();

        temp.put("id", blogEntity.getId());
        temp.put("title", blogEntity.getTitle());
        temp.put("content", blogEntity.getContent());
        temp.put("author", getJSONObject(blogEntity.getUserByUserId()));
        temp.put("publishTime", TimeUtils.getDateTime(blogEntity.getPublishTime()));
        temp.put("updateTime", TimeUtils.getDateTime(blogEntity.getUpdateTime()));

        return temp;
    }

}
