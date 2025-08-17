package br.com.lucasflix.Mapper;

import br.com.lucasflix.Controller.Request.UserRequest;
import br.com.lucasflix.Controller.Response.UserResponse;
import br.com.lucasflix.entity.User;


public class UserMapper {

    public static User toUser (UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }


        public static UserResponse toUserResponse (User user) {
            return UserResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
}
