use banking
go

delete pc_services where services_id = 73
go

delete ad_gb_services_tc where services_id = 73
go

insert into pc_services 
	(
	services_id,		effective_dt,		status,			status_sort,
	create_dt,		visible,		valid_dp,		valid_ln,
	valid_rm,		description,		option_1_label,		option_2_label,
	option_3_label,		option_4_label,		option_5_label,		parm_1_label,
	parm_2_label,		parm_3_label,		parm_4_label,		amt_1_label,
	amt_2_label,		amt_3_label,		amt_4_label,		cnt_1_label,
	cnt_2_label,		cnt_3_label,		cnt_4_label,		dt_1_label,
	dt_2_label,		dt_3_label,		dt_4_label,		verify_pin,
	pin_on_acct,		capture,		debug,			history_rows,
	origin_id,		avail_bal_cd,		avail_bal_ck,		avail_bal_ln,
	avail_bal_sv,		fuzzy_reversal,		memo_float_rate,	memo_float_limit,
	balance_odlimit,	balance_loc,		avail_bal_inq_cd,	avail_bal_inq_ck,
	avail_bal_inq_ln,	avail_bal_inq_sv,	balance_inq_odlimit,	balance_inq_loc,
	origin_type,		row_version,		memo_type,		ptid,
	empl_id,		gl_acct_proprietary,	gl_acct_non_proprietary,gl_acct_explicit_fees,
	pin_in_db,		no_days_log,		trusted_services,	valid_effective_dt,
	show_unverified,	post_today,		buy_exch_index_id,	buy_exch_rate_no,
	sell_exch_index_id,	sell_exch_rate_no,	allow_multi_crncy,	allow_rate_change,
	non_cash_pos,		trea_exch_index_id,	trea_exch_rate_no,	hold_days,
	partial_repost,		Synchronise_dates,	Last_Processing_dt
	)
values
	(
	73,			'20000101',		'Active',		10,
	'20000101',		'Y',			'Y',			'Y',
	'Y',			'eTranzact Integration to Equinox',null,	null,
	null,			null,			null,			null,
	null,			null,			null,			null,
	null,			null,			null,			null,
	null,			null,			null,			null,
	null,			null,			null,			null,
	'R',			'Y',			null,			null,
	12,			8,			8,			8,
	8,			null,			null,			null,
	null,			null,			8,			8,
	8,			8,			null,			null,
	'NET',			1,			'',			73,
	0,			null,			null,			null,
	null,			90,			'Y',			'N',
	null,			null,			null,			null,
	null,			null,			null,			null,
	null,			null,			null,			null,
	'N',			null, 			null
	)
go

insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			750,			'Y',			80,	
	'CD',			'20000101',		'Active',      		10,	
	'20000101',		0,			1,			80,
	'',			null,			157,			null,	
	'N',			null,			null,			null,	
	null
	)
go


insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			750,			'Y',			81,	
	'CK', 			'20000101',		'Active',      		10,	
	'20000101',		0,			1,			81,	
	'', 			null,			157,			null,	
	'N',			null,			null,			null,	
	null
	)
go

insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			750,			'Y',			82,	
	'SV', 			'20000101',		'Active',      		10,	
	'20000101',		0,			1,			82,
	'', 			null,			157,			null,	
	'N',			null,			null,			null,	
	null
	)
go
	
insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			750,			'Y',			83,	
	'CL', 			'20000101',		'Active',      		10,	
	'20000101',		0,			1,			83,	
	'', 			null,			350,			null,	
	'N',			null,			null,			null,	
	null
	)
go


insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			850,			'Y',			84,	
	'CD', 			'20000101',		'Active',      		10,	
	'20000101',		0,			1,			84,
	'', 			102,			163,			null,	
	'N',			null,			null,			null,	
	null
	)
go

insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			850,			'Y',			85,	
	'CK', 			'20000101',		'Active',      		10,
	'20000101',		0,			1,			85,
	'', 			102,			163,			null,
	'N',			null,			null,			null,
	null
	)
go

insert into ad_gb_services_tc 
	(
	services_id,		tran_code,		proprietary,		services_tc_id,
	appl_type,		effective_dt,		status,			status_sort,
	create_dt,		empl_id,		row_version,		ptid,
	memo_type,		credit_tran_code,	debit_tran_code,	description,
	use_description,	buy_exch_index_id,	buy_exch_rate_no,	sell_exch_index_id,
	sell_exch_rate_no
	)
values
	(
	73,			850,			'Y',			86,
	'SV', 			'20000101',		'Active',      		10,
	'20000101',		0,			1,			86,
	'', 			102,			163,			null,
	'N',			null,			null,			null,
	null
	)
go

use banking
go

if USER_ID('eswitch') is not null
begin
	exec sp_dropuser 'eswitch'
end
go

use xapi
go

if USER_ID('eswitch') is not null
begin
	exec sp_dropuser 'eswitch'
end
go


use master
go

if SUSER_ID('eswitch') is not null
begin
	exec sp_droplogin 'eswitch'
end
go


exec sp_addlogin 'eswitch','eswitch','banking','us_english'
go

use banking
go

if SUSER_ID('eswitch') is null
	exec sp_addlogin 'eswitch','PASSWORD'
go

exec sp_adduser 'eswitch','eswitch','grp_private'
go

use xapi
go

if SUSER_ID('eswitch') is null
	exec sp_addlogin 'eswitch','PASSWORD'
go

exec sp_adduser 'eswitch','eswitch','grp_atm'
go

