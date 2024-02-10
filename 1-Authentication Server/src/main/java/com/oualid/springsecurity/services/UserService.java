package com.oualid.springsecurity.services;

import com.oualid.springsecurity.models.Otp;
import com.oualid.springsecurity.models.User;
import com.oualid.springsecurity.repositories.OtpRepository;
import com.oualid.springsecurity.repositories.UserRespository;
import com.oualid.springsecurity.utils.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private OtpRepository otpRepository;


    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRespository.save(user);
    }
    public boolean auth(User user){
        Optional<User> optionalUser = userRespository.findUserByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User u = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                renewOtp(u);
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();

        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
        if (userOtp.isPresent()){
            Otp otp = userOtp.get();
            otp.setCode(code);
        }else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpToValidate){
        Optional<Otp> optionalOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        if (optionalOtp.isPresent()){
            Otp userOtp = optionalOtp.get();
            if (otpToValidate.getCode().equals(userOtp.getCode())){
                return true;
            }
        }
        return false;
    }
}
