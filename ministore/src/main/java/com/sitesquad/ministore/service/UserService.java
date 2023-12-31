/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitesquad.ministore.service;

import com.sitesquad.ministore.constant.SystemConstant;
import com.sitesquad.ministore.dto.UserDTO;
import com.sitesquad.ministore.model.ShiftRequest;
import com.sitesquad.ministore.model.User;
import com.sitesquad.ministore.model.UserShift;
import com.sitesquad.ministore.repository.RoleRepository;
import com.sitesquad.ministore.repository.ShiftRequestRepository;
import com.sitesquad.ministore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ACER
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ShiftRequestRepository shiftRequestRepository;

    SystemConstant SystemConstant = new SystemConstant();

    public User map2User(User oldUser){
        User newUser = new User();
        newUser.setUserId(oldUser.getUserId());
        newUser.setEmail(oldUser.getEmail());
        newUser.setName(oldUser.getName());
        newUser.setPhone(oldUser.getPhone());
        newUser.setRole(oldUser.getRole());
        newUser.setRoleId(oldUser.getRoleId());
        newUser.setAddress(oldUser.getAddress());
        newUser.setDob(oldUser.getDob());
        newUser.setUserImg(oldUser.getUserImg());
        return newUser;
    }

    public UserDTO mapUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRole().getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setDob(user.getDob());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setUserImg(user.getUserImg());
        return userDTO;
    }

    public Page<UserDTO> mapDTO(Page<User> userPage) {
        Page<UserDTO> userDTOPage = userPage.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setRoles(user.getRole().getName());
            userDTO.setAddress(user.getAddress());
            userDTO.setPhone(user.getPhone());
            userDTO.setDob(user.getDob());
            userDTO.setGender(user.getGender());
            userDTO.setUserImg(user.getUserImg());
            Boolean onLeave = false;
            try{
            List<ShiftRequest> shiftRequests = shiftRequestRepository.findByUserIdAndTypeTrue(user.getUserId());
            if(shiftRequests!=null) {
                for (ShiftRequest shiftRequest : shiftRequests) {
                    UserShift userShift= shiftRequest.getUserShift();
                    System.out.println(userShift);
                        if (SystemConstant.ZONE_DATE_TIME_NOW().isAfter(userShift.getStartTime())
                                && SystemConstant.ZONE_DATE_TIME_NOW().isBefore(userShift.getEndTime())) {
                            onLeave = true;
                            break;
                        }
                    }
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            userDTO.setOnLeave(onLeave);
            return userDTO;
        });
        return userDTOPage;
    }


    public User checkLogIn(String email, String password){
        return userRepository.findOneByEmailIgnoreCaseAndPasswordIgnoreCaseAndIsDeletedFalse(email,password);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAllExceptAdmin(){
        List<User> emp = userRepository.findByRoleId(new Long(2));
        List<User> guard = userRepository.findByRoleId(new Long(3));
        return Stream.concat(emp.stream(), guard.stream())
                .collect(Collectors.toList());
    }

    public UserDTO findUserByEmailNoPassword(String email){
        User user = userRepository.findOneByEmailIgnoreCaseAndIsDeletedFalse(email);
        UserDTO userNoPass = mapUserDTO(user);
        return userNoPass;
    }
    public User findUserByEmail(String email){
        return userRepository.findOneByEmailIgnoreCaseAndIsDeletedFalse(email);
    }
//    public Page<User> findAll(Integer offset){
//        Page<User> user = userRepository.findAllByIsDeletedFalse(PageRequest.of(offset,9));
//        return user;
//    }
    public Page<UserDTO> findAll(Integer offset) {
        Page<User> user = userRepository.findAllByIsDeletedFalse(PageRequest.of(offset, 9));
        Page<UserDTO> userDTOPage = mapDTO(user);
        return userDTOPage;
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findByUserIdAndIsDeletedFalse(id);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setName(user.getName());
            userDTO.setRoles(user.getRole().getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setDob(user.getDob());
            userDTO.setGender(user.getGender());
            userDTO.setAddress(user.getAddress());
            userDTO.setUserImg(user.getUserImg());
            return userDTO;
        } else {
            return null;
        }
    }

    public User find(Long id){
        return userRepository.findByUserIdAndIsDeletedFalse(id);
    }

    public boolean delete(Long id) {
        User user = userRepository.findByUserIdAndIsDeletedFalse(id);
        if (user == null) {
            return false;
        } else {
            user.setIsDeleted(true);
            userRepository.save(user);
            return true;
        }
    }

    public boolean checkUserExist(String email, String phone) {
        return userRepository.findOneByEmailIgnoreCaseOrPhoneAndIsDeletedFalse(email, phone) != null;
    }


    public UserDTO convertUserToUserDTO(User user){
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setName(user.getName());
            userDTO.setRoles(user.getRole().getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setDob(user.getDob());
            userDTO.setGender(user.getGender());
            userDTO.setAddress(user.getAddress());
            userDTO.setUserImg(user.getUserImg());
            return userDTO;
        } else {
            return null;
        }
    }
        public List<User> findUserByRoleName(String name) {
        List<User> users = userRepository.findUserByRole_NameIgnoreCaseAndIsDeletedFalse(name);
//        List<UserDTO> userDTOs = new ArrayList<>();
//        for (User user: users) {
//            userDTOs.add(convertUserToUserDTO(user));
//        }
        return users;
    }


    public User add (String name,String email,String phone,String address,String dob, String gender ,Long roleId, String userImg){
        User user = new User();


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dobDate = dateFormat.parse(dob);
            user.setDob(dobDate);
        } catch (Exception e) {
            System.out.println(e);
        }

        Boolean genderBoolean = null;
        if (gender.trim().toLowerCase() == "male") {
            genderBoolean = true;
        } else {
            genderBoolean = false;
        }
        user.setGender(genderBoolean);

        user.setIsDeleted(false);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserImg(userImg);
        user.setRole(roleRepository.findByRoleIdAndIsDeletedFalse(roleId));
        user.setPassword("1");
        return userRepository.save(user);
    }

    public User addUser(User user){
        user.setIsDeleted(Boolean.FALSE);
        user.setRole(roleRepository.findByRoleIdAndIsDeletedFalse(user.getRoleId()));
        user.setPassword("A12345678");
        return userRepository.save(user);
    }

    public User addUserForEdit(User user, String password){
        user.setIsDeleted(Boolean.FALSE);
        user.setRole(roleRepository.findByRoleIdAndIsDeletedFalse(user.getRoleId()));
        user.setPassword(password);
        return userRepository.save(user);
    }
// old edit

//    public User edit (User newUser){
//        User oldUser = userRepository.findOneByEmailIgnoreCaseAndIsDeletedFalse(newUser.getEmail());
//        if(oldUser == null)
//            return null;
//        newUser.setUserId(null);
//
//        User userChanged =addUserForEdit(newUser, oldUser.getPassword());
//        if(userChanged != null){
//            oldUser.setIsDeleted(Boolean.TRUE);
//            userRepository.save(oldUser);
//        }
//        return userChanged;
//    }



    public User edit (User newUser){
        User oldUser = userRepository.findOneByEmailIgnoreCaseAndIsDeletedFalse(newUser.getEmail());
        if(oldUser == null)
            return null;
        oldUser.setName(newUser.getName());
        oldUser.setUserImg(newUser.getUserImg());
        oldUser.setDob(newUser.getDob());
        oldUser.setAddress(newUser.getAddress());
        oldUser.setGender(newUser.getGender());

        oldUser.setRoleId(newUser.getRoleId());
        oldUser.setRole(roleRepository.findById(newUser.getRoleId()).get());
        oldUser.setIsDeleted(Boolean.FALSE);


        User userChanged = userRepository.save(oldUser);
        return userChanged;
    }

    //change old password
//    public User changePassword(User oldUser,String oldPassword,String newPassword){
//
//
//
//        if(oldUser == null)
//            return null;
//
//
//        User userChanged = new User();
//        userChanged.setEmail(oldUser.getEmail());
//        userChanged.setName(oldUser.getName());
//        userChanged.setGender(oldUser.getGender());
//        userChanged.setPhone(oldUser.getPhone());
//        userChanged.setDob(oldUser.getDob());
//        userChanged.setUserImg(oldUser.getUserImg());
//        userChanged.setAddress(oldUser.getAddress());
//        userChanged.setRoleId(oldUser.getRoleId());
//        userChanged.setRole(oldUser.getRole());
//        addUserForEdit(userChanged,newPassword);
//
//
//        if(userChanged != null){
//            oldUser.setIsDeleted(Boolean.TRUE);
//            userRepository.save(oldUser);
//        }
//        return userChanged;
//    }

    public User changePassword(User user,String newPassword){
        User oldUser = userRepository.findOneByEmailIgnoreCaseAndIsDeletedFalse(user.getEmail());
        oldUser.setPassword(newPassword);
        User userChanged = userRepository.save(oldUser);
        return userChanged;
    }

}
