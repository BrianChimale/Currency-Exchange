package com.chimale.currencyexchange.model;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.View;

import com.chimale.currencyexchange.R;


/**
 * Created by HP PAVILION on 10/4/2017.
 */

public class Currencies {

    public static String currenciesCodes[]={"NGN", "AUD", "BRL", "CAD", "CHF", "CNY", "DKK", "GBP", "HKD", "HUF", "INR", "IDR", "ILS", "JPY", "KRW", "RUB", "SEK", "USD","EUR","KES"};
    public static String Countries[] = {"Nigerian Naira", "Australian Dollar", "Brazilian Real", "Canadian Dollar", "Swiss Franc", "Chinese Renminbi (Yuan)", "Danish Krone", "United Kingdom Pound Sterling", "Hong Kong Dollar", "Hungary Forint", "Indian Rupee", "Indonesian Rupiah", "New Israeli Sheqel", "Japanese Yen", "South Korean Won", "Russian Ruble", "Swedish Krona", "US Dollar", "Euro", "Kenyan Shilling"};

    public static int getImageResourceId(int index){

        int resourceId;

        switch (index){
            case 0:
                resourceId = R.drawable.ic_list_ng;
                break;
            case 1:
                resourceId = R.drawable.ic_list_au;
                break;
            case 2:
                resourceId = R.drawable.ic_list_br;
                break;
            case 3:
                resourceId = R.drawable.ic_list_ca;
                break;
            case 4:
                resourceId = R.drawable.ic_list_ch;
                break;
            case 5:
                resourceId = R.drawable.ic_list_cn;
                break;
            case 6:
                resourceId = R.drawable.ic_list_dk;
                break;
            case 7:
                resourceId = R.drawable.ic_list_uk;
                break;
            case 8:
                resourceId = R.drawable.ic_list_hk;
                break;
            case 9:
                resourceId = R.drawable.ic_list_hu;
                break;
            case 10:
                resourceId = R.drawable.ic_list_in;
                break;
            case 11:
                resourceId = R.drawable.ic_list_id;
                break;
            case 12:
                resourceId = R.drawable.ic_list_il;
                break;
            case 13:
                resourceId = R.drawable.ic_list_jp;
                break;
            case 14:
                resourceId = R.drawable.ic_list_kr;
                break;
            case 15:
                resourceId = R.drawable.ic_list_ru;
                break;
            case 16:
                resourceId = R.drawable.ic_list_se;
                break;
            case 17:
                resourceId = R.drawable.ic_list_us;
                break;
            case 18:
                resourceId = R.drawable.ic_list_eu;
                break;
            case 19:
                resourceId = R.drawable.ic_list_ke;
                break;
            default:
                resourceId = R.drawable.ic_list_us;
                break;
        }

        return resourceId;
    }
}
