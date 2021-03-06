package com.hindsight.authdemo.repository;

import com.hindsight.authdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserJpaRepo extends JpaRepository<User, String> {


//    Optional<User> findByNicknameAndProviderName(String nickname, String provider);
//    Optional<User> findByOauthIdAndProviderName(String ouathId, String provider);
//    User findByNickname(String nickname);


    Optional<User> findUserByJwtAccess(String jwtToken);

    User findUserByUidAndFlag(long Uid, Boolean flag);       // User테이블에서 Uid

    User findUserByAccessTokenAndFlag(String accessToken, Boolean flag);
//    Optional<User> findUserByNickname(String nickname);

    Optional<User> findUserByJwtAccessAndUid(String jwtToken,long Uid);
    User findUserByUid(long Uid);

    Optional<User> findUserByNickname(String nickname);

}
