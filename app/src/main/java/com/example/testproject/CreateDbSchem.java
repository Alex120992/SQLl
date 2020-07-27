package com.example.testproject;

import android.provider.BaseColumns;

public class CreateDbSchem {


    private CreateDbSchem() {

    }

    public static final class Table implements BaseColumns {

        public static final String TABLE_NAME = "Base_Data";

        public static final class Columns {

            public static final String NUMBER = "number";
            public static final String DATA = "data";
        }

    }

}
