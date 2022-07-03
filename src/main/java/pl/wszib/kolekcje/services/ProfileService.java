package pl.wszib.kolekcje.services;

import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.web.mappers.ProfileMapper;
import pl.wszib.kolekcje.web.models.ProfileModel;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void saveProfile(Long idUser, ProfileModel profileModel) {

        ProfileEntity profileEntity = ProfileMapper.toEntity(profileModel);
        ProfileEntity profileEntity1 = new ProfileEntity();

        profileEntity.setUserName(profileEntity1.getUserName());
        profileEntity.setLoginName(profileEntity1.getLoginName());
        profileEntity.setPassword(profileEntity1.getPpassword());
        profileEntity.setAddressEmail(profileEntity1.getAddressEmail());
        profileEntity.setStatus(profileEntity1.getStatus());
        profileEntity.setDateOfRegistration(profileEntity1.getDateOfRegistration());

        profileRepository.save(profileEntity1);

    }



}
