package dog.kiara.archerybackend.service;

import dog.kiara.archerybackend.entity.AppUser;
import dog.kiara.archerybackend.entity.Parcours;
import dog.kiara.archerybackend.entity.PlayedGame;
import dog.kiara.archerybackend.repository.AppUserRepository;
import dog.kiara.archerybackend.repository.ParcoursRepository;
import dog.kiara.archerybackend.repository.PlayedGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArcheryService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private PlayedGameRepository playedGameRepository;

    public List<AppUser> selectAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser searchUserByNickname(String nickname) {

        List<AppUser> appUsersByNickname = appUserRepository.findAppUsersByNickname(nickname);
        if (appUsersByNickname.isEmpty()) {
            return null;
        }
        return appUsersByNickname.get(0);
    }

    public void saveUserToDatabase(String nickname, String firstname, String lastname, String password) {

        AppUser appUser = new AppUser(firstname, lastname, nickname, password);

        appUserRepository.saveAndFlush(appUser);
    }

    public List<Parcours> selectAllParcours() {
        return parcoursRepository.findAll();
    }

    public void saveCreatedUserToDatabase(String firstname, String lastname, String nickname, String createdBy) {

        Integer createdByID = appUserRepository.findAppUsersByNickname(createdBy).get(0).getUserId();

        AppUser createdUser = new AppUser(firstname, lastname, nickname, createdByID);

        appUserRepository.saveAndFlush(createdUser);

    }

    public List<PlayedGame> selectAllPlayedGames() {
        return playedGameRepository.findAll();
    }

    public List<AppUser> getAllRegisteredUser() {
        return appUserRepository.findAllByCreatedByIsNull();
    }

    public void saveAverage(int scoredPoints) {

        PlayedGame playedGame = new PlayedGame(scoredPoints);

        playedGameRepository.saveAndFlush(playedGame);
    }

    public List<AppUser> getUsersByCreatorId(int creatorId) {
        return appUserRepository.findUserByCreator(creatorId);
    }

    public List<Parcours> getParcoursByUser(int userId) {
        return parcoursRepository.findParcoursByCreatedUser(userId);
    }

    public void createParcour(String name, String location, int number, AppUser appUser) {
        parcoursRepository.saveAndFlush(new Parcours(name, location, number, appUser));
    }

    public void createUser(String firstName, String lastName, String username, String password) {
        AppUser appUser = new AppUser(firstName, lastName, username, password);
        appUserRepository.saveAndFlush(appUser);
    }

    public boolean userExists(String username) {
        return appUserRepository.existsByNickname(username);
    }
}