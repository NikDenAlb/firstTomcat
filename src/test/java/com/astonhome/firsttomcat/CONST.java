package com.astonhome.firsttomcat;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.dto.UserPrivateDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CONST {
    public static List<UserDTO> USERSOFCOACH1DTO = new ArrayList<>();
    public static CoachDTO COACH1DTO = new CoachDTO(1L, "coachName1", USERSOFCOACH1DTO);

    public static List<UserDTO> USERSOFCOACH2DTO = null;
    public static CoachDTO COACH2DTO = new CoachDTO(2L, "coachName2", USERSOFCOACH2DTO);

    public static List<CoachDTO> COACHOFUSER1DTO = new ArrayList<>();
    public static UserDTO USER1DTO = new UserDTO(1L, "userName1", COACHOFUSER1DTO);

    public static List<CoachDTO> COACHOFUSER2DTO = null;
    public static UserDTO USER2DTO = new UserDTO(2L, "userName2", COACHOFUSER2DTO);

    public static List<CoachDTO> COACHOFUSER3DTO = new ArrayList<>(List.of(COACH1DTO, COACH2DTO));
    public static UserDTO USER3DTO = new UserDTO(3L, "userName3", COACHOFUSER3DTO);

    public static List<UserDTO> USERSOFCOACH3DTO = new ArrayList<>(List.of(USER1DTO, USER2DTO, USER3DTO));
    public static CoachDTO COACH3DTO = new CoachDTO(3L, "coachName3", USERSOFCOACH3DTO);
    //
    public static List<User> USERSOFCOACH1 = new ArrayList<>();
    public static Coach COACH1 = new Coach(1L, "coachName1", USERSOFCOACH1);

    public static List<User> USERSOFCOACH2 = null;
    public static Coach COACH2 = new Coach(2L, "coachName2", USERSOFCOACH2);

    public static List<Coach> COACHOFUSER1 = new ArrayList<>();
    public static User USER1 = new User(1L, "userName1", COACHOFUSER1, null);

    public static List<Coach> COACHOFUSER2 = null;
    public static User USER2 = new User(2L, "userName2", COACHOFUSER2, "Здоров");

    public static List<Coach> COACHOFUSER3 = new ArrayList<>(List.of(COACH1, COACH2));
    public static User USER3 = new User(3L, "userName3", COACHOFUSER3, "Болен");

    public static List<User> USERSOFCOACH3 = new ArrayList<>(List.of(USER1, USER2, USER3));
    public static Coach COACH3 = new Coach(3L, "coachName3", USERSOFCOACH3);
    ////////////
    public static CoachUpdateDTO COACHUPDATEDTO1 = new CoachUpdateDTO(1L, "coachName1");
    public static CoachUpdateDTO COACHUPDATEDTO2 = new CoachUpdateDTO(2L, "coachName2");
    public static CoachUpdateDTO COACHUPDATEDTO3 = new CoachUpdateDTO(3L, "coachName3");
    ////////////
    public static UserPrivateDTO USERPRIVATEDTO1 = new UserPrivateDTO(1L, "userName1", null, null);
    public static UserPrivateDTO USERPRIVATEDTO2 = new UserPrivateDTO(2L, "userName2", COACHOFUSER2DTO, "Здоров");
    public static UserPrivateDTO USERPRIVATEDTO3 = new UserPrivateDTO(3L, "userName3", COACHOFUSER3DTO, "Болен");

}