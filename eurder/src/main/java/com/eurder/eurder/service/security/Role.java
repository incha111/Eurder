package com.eurder.eurder.service.security;

import java.util.List;

import static com.eurder.eurder.service.security.Feature.*;
import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList(
            ADD_ITEM,
            ITEMS_SHIPPING_TODAY,
            UPDATE_ITEM,
            VIEW_ALL_CUSTOMER,
            VIEW_ALL_ORDERS,
            ITEMS_OVERVIEW,
            VIEW_SINGLE_CUSTOMER,
            VIEW_SINGLE_ITEM,
            VIEW_SINGLE_ORDER
    )),
    CUSTOMER(newArrayList(
            CREATE_ORDER,
            RE_ORDER,
            VIEW_ORDER_REPORT
    ));

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
