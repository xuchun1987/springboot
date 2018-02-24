package boot.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LsysUser implements Serializable,UserDetails {


    private Integer id;

    private String name;

    private String username;

    private String userpass;

    private Integer statue;

    private String udesc;

    private String seller_bn;

    private String mer_no;

    private Integer p_id;

    private String level;

    private String store_bn;
    private String role;

    List<LsysRole> roles;//多角色

    List<LsysResource> resources;//放这边是为了合并role对应的资源（有可能不同role分配了相同的resources）

    public List<LsysResource> getResources() {
        return resources;
    }

    public void setResources(List<LsysResource> resources) {
        this.resources = resources;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        if(!CollectionUtils.isEmpty(roles)){
            for(LsysRole lr:roles){
                auths.add(new SimpleGrantedAuthority(lr.getName()));
            }
        }
        return auths;
    }


    @Override
    public String getPassword() {
        return getUserpass();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }

    public String getSeller_bn() {
        return seller_bn;
    }

    public void setSeller_bn(String seller_bn) {
        this.seller_bn = seller_bn;
    }

    public String getMer_no() {
        return mer_no;
    }

    public void setMer_no(String mer_no) {
        this.mer_no = mer_no;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStore_bn() {
        return store_bn;
    }

    public void setStore_bn(String store_bn) {
        this.store_bn = store_bn;
    }

    public List<LsysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<LsysRole> roles) {
        this.roles = roles;
    }

    public String getRoleIds(){

        if(!CollectionUtils.isEmpty(roles)){
            List<String> list=new ArrayList<String>();
            for(LsysRole lr:roles){
                list.add(String.valueOf(lr.getId()));
            }
           return String.join(",", list);
        }
        return "";
    }

    public String getRoleNames(){

        if(!CollectionUtils.isEmpty(roles)){
            List<String> list=new ArrayList<String>();
            for(LsysRole lr:roles){
                list.add(lr.getName());
            }
            return String.join(",", list);
        }
        return "";
    }
}
