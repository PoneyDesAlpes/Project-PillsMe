package be.helha.pills_me.db;

public abstract class PillsMeDbSchema {
    public static final class PillTable {
        public static final String NAME = "pills";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DURATION = "duration";
            public static final String MORNING = "morning";
            public static final String MIDDAY = "midday";
            public static final String EVENING = "evening";
        }
    }

    public static final class PrescriptionTable {
        public static final String NAME = "prescriptions";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String START_DATE_PERIOD = "startOfPeriod";
            public static final String END_DATE_PERIOD = "endOfPeriod";
            public static final String MORNING = "morning";
            public static final String MIDDAY = "midday";
            public static final String EVENING = "evening";
            public static final String PILL_ID = "pillId";
        }
    }
}
