package boot.entity;

import java.io.Serializable;

public class LsysRole implements Serializable{

    private Integer id;

    private String name;

    private String userDesc;

    private Integer is_merchant;


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

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getIs_merchant() {
        return is_merchant;
    }

    public void setIs_merchant(Integer is_merchant) {
        this.is_merchant = is_merchant;
    }
}
