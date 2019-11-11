package com.social.troops.domain.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({DataRequestType.USER_LOGIN, DataRequestType.USER_REGISTER, DataRequestType.FORGOT_PASSWORD, DataRequestType.RESET_PASSWORD,
        DataRequestType.FEATURED_VENDORS, DataRequestType.FEATURED_CATEGORIES, DataRequestType.FEATURED_OFFERS,
        DataRequestType.ALL_OFFERS, DataRequestType.ALL_CATEGORIES,DataRequestType.GET_PROFILE, DataRequestType.UPDATE_PROFILE,
        DataRequestType.VERIFY_OTP_FORGOT_PASSWORD,
        DataRequestType.OFFER_DETAILS,
        DataRequestType.VENDOR_OFFERS, DataRequestType.CHANGE_PASSWORD, DataRequestType.OFFER_SUGGESTIONS,
        DataRequestType.HOME_SEARCH, DataRequestType.CATEGORY_OFFERS, DataRequestType.VERIFY_OTP_REGISTRATION,
        DataRequestType.RESEND_OTP_REGISTRATION, DataRequestType.RESEND_OTP_FORGOT_PASSWORD, DataRequestType.VENDORS,
        DataRequestType.GET_WALLET_DETAILS, DataRequestType.GET_WALLET_HISTORY,
        DataRequestType.GET_REDEEM_ACCOUNTS, DataRequestType.ADD_REDEEM_ACCOUNT, DataRequestType.REDEEM_WALLET_BALANCE,
        DataRequestType.GET_REDEEM_HISTORY, DataRequestType.DELETE_REDEEM_ACCOUNT,
        DataRequestType.APPLY_OFFER,
        DataRequestType.CONTACT_US,
        DataRequestType.TERMS,
        DataRequestType.PRIVACY_POLICY,
        DataRequestType.ABOUT_US,
        DataRequestType.FAQ,
})
public @interface DataRequestType {

    int USER_LOGIN = 101;
    int USER_REGISTER = 102;
    int FORGOT_PASSWORD = 103;
    int RESET_PASSWORD = 104;
    int FEATURED_VENDORS = 105;
    int FEATURED_CATEGORIES = 106;
    int FEATURED_OFFERS = 107;
    int ALL_OFFERS = 108;
    int ALL_CATEGORIES = 109;
    int GET_PROFILE = 110;
    int UPDATE_PROFILE = 111;
    int VERIFY_OTP_FORGOT_PASSWORD = 112;
    int OFFER_DETAILS = 113;
    int VENDOR_OFFERS = 114;
    int CHANGE_PASSWORD = 115;
    int OFFER_SUGGESTIONS = 116;
    int HOME_SEARCH = 117;
    int CATEGORY_OFFERS = 118;
    int VERIFY_OTP_REGISTRATION = 119;
    int RESEND_OTP_REGISTRATION = 120;
    int RESEND_OTP_FORGOT_PASSWORD = 121;
    int VENDORS = 122;

    int GET_WALLET_DETAILS = 123;
    int GET_WALLET_HISTORY = 124;
    int GET_REDEEM_ACCOUNTS = 125;
    int ADD_REDEEM_ACCOUNT = 126;
    int REDEEM_WALLET_BALANCE = 127;
    int GET_REDEEM_HISTORY = 128;
    int DELETE_REDEEM_ACCOUNT = 129;

    int APPLY_OFFER = 130;


    int ABOUT_US = 131;
    int CONTACT_US = 132;
    int TERMS = 133;
    int PRIVACY_POLICY = 134;
    int FAQ = 135;

    int SAVE_REFERAL_CODE = 136;
    int GET_REFERAL_CODE = 137;


    int USER_UPDATE_ROLE = 138;
    int FILTER_LIST = 139;
    int SEARCH_PRODUCT = 140;
    int SELLER_PRODUCTS = 141;
    int BUYER_PRODUCTS = 142;
    int MESSAGE_LIST = 143;
    int MESSAGE_USER_LIST = 144;
    int ADD_TO_WATCH_LIST = 145;
    int DELETE_PRODUCT = 146;
}
