create table data_pid
(
    id bigserial not null,
    dospeli integer,
    kod varchar(255),
    junior integer,
    mesiac varchar(255),
    prenosna integer,
    dochodcovia integer,
    studenti integer,
    typ_predaja varchar(255),
    platnost varchar(255),
    rok integer,
    dieta integer
);

create table pid_jizdenky
(
    id bigserial not null,
    kod varchar(255),
    zlavneny boolean,
    osem_pasem bigint,
    jedenast_pasem bigint,
    stvrt_minut bigint,
    pat_pasem bigint,
    styri_pasma bigint,
    mesiac varchar(255),
    devat_pasem bigint,
    jeden_den bigint,
    jeden_den_vsetky bigint,
    sedem_pasem bigint,
    sest_pasem bigint,
    desat_pasem bigint,
    tri_pasma bigint,
    dve_pasma bigint,
    rok integer
);



