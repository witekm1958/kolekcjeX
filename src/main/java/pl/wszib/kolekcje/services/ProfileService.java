package pl.wszib.kolekcje.services;

import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.web.mappers.ProfileMapper;
import pl.wszib.kolekcje.web.models.ProfileModel;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    Date date = new Date();

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void saveProfile(ProfileModel profileModel) {

        ProfileEntity profileEntity = ProfileMapper.toEntity(profileModel);

        profileEntity.setDateOfRegistration(date);

        // Zamienić na ENUM
        profileEntity.setStatus("NEW");

        profileRepository.save(profileEntity);

    }



}
