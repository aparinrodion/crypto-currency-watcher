create table crypto_currency
(
    id          bigserial primary key,
    external_id bigint       not null unique,
    symbol      varchar(5)   not null unique,
    name        varchar(128) not null unique,
    price_usd   decimal      not null,
    updated_at  timestamp    not null
)
