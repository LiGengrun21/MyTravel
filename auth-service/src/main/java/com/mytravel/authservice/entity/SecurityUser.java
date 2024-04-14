//package com.mytravel.authservice.entity;
//
//import com.mytravel.authservice.entity.dto.User;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
///**
// * @author Li Gengrun
// * @date 2024/3/29 14:48
// */
//
//@Data
//public class SecurityUser implements UserDetails {
//
//    private Integer id;
//
//    private String username;
//
//    private String password;
//
//    private String email;
//
//    //user status
//    private boolean enabled;
//
//    private Collection<SimpleGrantedAuthority> authorities;
//
//    public SecurityUser(){
//
//    }
//
//    public SecurityUser(User userDto){
//        this.setId(userDto.getId());
//        this.setUsername(userDto.getUsername());
//        this.setPassword(userDto.getPassword());
//        this.setEmail(userDto.getEmail());
//        this.setEnabled(userDto.getStatus()==1);
//        if (userDto.getRole() != null) {
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(userDto.getRole()));
//        }
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//}
