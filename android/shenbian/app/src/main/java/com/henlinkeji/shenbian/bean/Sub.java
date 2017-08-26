package com.henlinkeji.shenbian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Miracler on 17/2/13.
 */

public class Sub implements IPickerViewData {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
