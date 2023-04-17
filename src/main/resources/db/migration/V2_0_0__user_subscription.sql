create table user_subscription
(
    id                     bigserial primary key,
    username               varchar(32) not null,
    subscription_price_usd decimal     not null,
    currency_id            bigint      not null references crypto_currency (id),
    unique (username, currency_id)
)