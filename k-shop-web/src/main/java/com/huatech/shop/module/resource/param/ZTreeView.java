package com.huatech.shop.module.resource.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ztree树
 */
@Data

public class ZTreeView implements Serializable {


    private Long id;

    private Long pid;

    private String name;

    private boolean open;

    private boolean checked = false;


    public ZTreeView(Long id, Long pId, String name, boolean open) {
        super();
        this.id = id;
        this.pid = pId;
        this.name = name;
        this.open = open;
    }

    public ZTreeView() {

    }


}
