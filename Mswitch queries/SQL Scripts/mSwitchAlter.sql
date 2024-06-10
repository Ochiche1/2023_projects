alter table pc_services
add  host_port               numeric(12,0) DEFAULT 0 NOT NULL,
    switch_name             varchar(200)  DEFAULT '' NOT NULL,
    host_addr               varchar(200)  DEFAULT 'localhost' NOT NULL
