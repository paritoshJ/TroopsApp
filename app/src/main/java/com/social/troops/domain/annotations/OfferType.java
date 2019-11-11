package com.social.troops.domain.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({OfferType.IN_STORE, OfferType.ONLINE
})
public @interface OfferType {

    // 1=online offer, 2=in-store offer
    int IN_STORE = 2;
    int ONLINE = 1;
}
