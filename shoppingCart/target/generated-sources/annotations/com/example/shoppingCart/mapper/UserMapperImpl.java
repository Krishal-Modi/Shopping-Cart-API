package com.example.shoppingCart.mapper;

import com.example.shoppingCart.entity.User;
import com.example.shoppingCart.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-29T21:59:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel userToUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setUserId( user.getUserId() );
        userModel.setFirstName( user.getFirstName() );
        userModel.setLastName( user.getLastName() );
        userModel.setEmail( user.getEmail() );
        userModel.setPhoneNumber( user.getPhoneNumber() );
        userModel.setAddress( user.getAddress() );
        userModel.setPassword( user.getPassword() );
        userModel.setDob( user.getDob() );

        return userModel;
    }

    @Override
    public User userModelToUser(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userModel.getUserId() );
        user.setFirstName( userModel.getFirstName() );
        user.setLastName( userModel.getLastName() );
        user.setEmail( userModel.getEmail() );
        user.setPassword( userModel.getPassword() );
        user.setPhoneNumber( userModel.getPhoneNumber() );
        user.setAddress( userModel.getAddress() );
        user.setDob( userModel.getDob() );

        return user;
    }

    @Override
    public User updateUserModel(UserModel userModel, User user) {
        if ( userModel == null ) {
            return user;
        }

        user.setUserId( userModel.getUserId() );
        user.setFirstName( userModel.getFirstName() );
        user.setLastName( userModel.getLastName() );
        user.setEmail( userModel.getEmail() );
        user.setPassword( userModel.getPassword() );
        user.setPhoneNumber( userModel.getPhoneNumber() );
        user.setAddress( userModel.getAddress() );
        user.setDob( userModel.getDob() );

        return user;
    }
}
