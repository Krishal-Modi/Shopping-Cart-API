package com.example.shoppingCart.mapper;

import com.example.shoppingCart.entity.Role;
import com.example.shoppingCart.model.RoleModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-29T21:59:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleModel rolesToRolesModel(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setRoleId( role.getRoleId() );
        roleModel.setRoleName( role.getRoleName() );

        return roleModel;
    }

    @Override
    public List<RoleModel> rolesToRolesModel(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleModel> list = new ArrayList<RoleModel>( roles.size() );
        for ( Role role : roles ) {
            list.add( rolesToRolesModel( role ) );
        }

        return list;
    }
}
