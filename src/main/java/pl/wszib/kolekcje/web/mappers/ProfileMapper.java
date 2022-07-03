package pl.wszib.kolekcje.web.mappers;

import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.web.models.ProfileModel;

public class ProfileMapper {

    public static ProfileEntity toEntity(ProfileModel model) {
        ProfileEntity entity = new ProfileEntity();
        entity.setUserName(model.getUserName());
        entity.setLoginName(model.getLoginName());
        entity.setPassword(model.getPassword());
        entity.setAddressEmail(model.getAddressEmail());
        return entity;
    }
}
