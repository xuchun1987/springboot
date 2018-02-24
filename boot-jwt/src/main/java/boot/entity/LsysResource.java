package boot.entity;

import java.util.List;

public class LsysResource {

    private Integer id;

    private String rname;

    private Integer rtype;

    private Integer p_id;

    private String rstring;

    private Integer priority;

    private Integer is_show;

    private String rdesc;

    private Integer sort;

    private List<LsysResource> list;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<LsysResource> getList() {
        return list;
    }

    public void setList(List<LsysResource> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getRstring() {
        return rstring;
    }

    public void setRstring(String rstring) {
        this.rstring = rstring;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }


}
