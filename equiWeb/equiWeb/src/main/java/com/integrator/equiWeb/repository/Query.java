package com.integrator.equiWeb.repository;

public final class Query {
    public static final String GL_QUERY = "select gl_acct_non_proprietary from PC_SERVICES WHERE SERVICES_ID = SERVICES_ID";
    public static final String SELECT_QUERY_FROM_PC_SERVICE = "select branch_no as sBranchNo, clearing_acct as sClearingGL, receivable_acct as sReceivableGL from pc_services_terminal where services_id = SERVICES_ID and terminal_id = TERMINAL_ID";
    public static final String SQL_TXN_REVERSAL = "SELECT A.SYSTEM_REF, A.TRAN_JOURNAL_ID, A.ACCT_AMT, A.TRAN_AMT, A.ACCT_NO, A.CONTRA_ACCT_NO, A.ACCT_CRNCY_ID,TO_CHAR( A.TRAN_DT, 'YYYY-MM-DD') TRAN_DT, A.TRAN_REF_TXT, B.CRNCY_CD_ISO FROM TXN_JOURNAL A, CURRENCY B WHERE A.ACCT_CRNCY_ID = B.CRNCY_ID AND TRAN_REF_TXT = ?";
    public static final String INSERT_INTO_ACCESS_LOG = "Insert into SERVICE_CHANNEL_ACCESS_LOG\n" +
        "   (CREATED_BY, USER_ID, CREATE_DT, REC_ST, SYS_CREATE_TS, \n" +
        "    ROW_TS, VERSION_NO, ACCT_NO, CONTRA_ACCT_NO, ACTIVITY_AMT, \n" +
        "    ACTIVITY_CRNCY_CD, CARD_NO, EFFECTIVE_DT, OPERATION_CD, \n" +
        "    ORIG_REF_NO, REF_NO,  REQUEST_DT, REVERSAL_IND, SWITCH_ID, \n" +
        "    TERMINAL_ID, USER_ACCESS_CD, USER_NAME, SERVICE_CHANNEL, ERROR_CODE, \n" +
        "    LOCAL_EQUIVALENT_TRAN_AMT, PROCESSING_CODE, RETURN_CODE)\n" +
        " Values\n" +
        "   (?, ?, GETDATE(), ?, GETDATE(), GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,GETDATE(), ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?)";
}
