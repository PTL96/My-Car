package org.example.carshop.controller;

import org.example.carshop.dto.response.JwtResponse;
import org.example.carshop.dto.response.ResponseMessage;
import org.example.carshop.dto.resquest.SignInForm;
import org.example.carshop.dto.resquest.SignUpForm;
import org.example.carshop.entity.accout.Account;
import org.example.carshop.entity.accout.Role;
import org.example.carshop.entity.accout.RoleName;
import org.example.carshop.jwt.JWTProvider;
import org.example.carshop.principle.AccountPrinciple;
import org.example.carshop.service.IAccountService;
import org.example.carshop.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = accountPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getId(),
                accountPrinciple.getUsername(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                accountPrinciple.getIdCard(),
                accountPrinciple.getAddress(),
                accountPrinciple.getDateOfBirth(),
                accountPrinciple.getPhoneNumber(),
                roles));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (iAccountService.existsAccountByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Tên đăng nhập " + signUpForm.getUsername() +
                    " đã được sử dụng, vui lòng chọn tên khác"), HttpStatus.BAD_REQUEST);
        }
        if (iAccountService.existsAccountByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Account account = new Account(signUpForm.getUsername(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getName(), signUpForm.getEmail());
        Set<Role> roles = new HashSet<>();
        Role role = iRoleService.findByName(RoleName.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(role);
        account.setRoleSet(roles);
        iAccountService.save(account);
        return new ResponseEntity<>(new ResponseMessage("Đăng kí thành công"), HttpStatus.OK);
    }

}
