package com.orbit.transaction.inward.exceptions;

public class ResponseConstants {
    public static final String SUCCEESS_CODE = "00";
    public static final String SUCCEESS_MESSAGE = "Successful";
    public static final String UNSUCCESSFUL = "Unsuccessful Transaction";
    public static final String ACCOUNT_NUMBER_NOT_FOUND = "Account number not found";
    public static final String DORMANT_ACCOUNT_NUMBER = "This account is dormant";
    public static final String DORMANT_ACCOUNT_NUMBER_CODE = "-11701";
    public static final String ACCOUNT_NUMBER_NOT_FOUND_CODE = "-50";

    public static final String RECORD_FOUND = "No transactions found in the database";
    //insufficient funds
    public static final String INSUFFICIENT_CODE = "01";
    public static final String INSUFFICIENT_MESSAGE = "Insufficient Funds";

    // sybase
    public static final String SYBASE_DATABASE = "SYBASE";
    public static final String SYBASE_DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
    public static final String SYBASE_CONNECTION_URL_PREFIX = "jdbc:sybase:Tds:";
}
