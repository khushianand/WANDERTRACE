create table trip_members (
  id uuid primary key default uuid_generate_v4(),
  trip_id uuid not null references trips(id) on delete cascade,
  user_id uuid not null references users(id) on delete cascade,
  role varchar(20) not null check (role in ('OWNER','EDITOR','VIEWER')),
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now(),
  unique (trip_id, user_id)
);
create index idx_trips_owner on trips(user_id);
create index idx_trip_members_trip on trip_members(trip_id);
create index idx_trip_members_user on trip_members(user_id);
