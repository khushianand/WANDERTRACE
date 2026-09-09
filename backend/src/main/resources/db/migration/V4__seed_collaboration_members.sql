insert into users(id,email,password_hash,display_name,role) values
('00000000-0000-0000-0000-000000000002','friend@wandertrace.local','$2a$10$development-only-placeholder-not-a-valid-production-secret','Paris Friend','USER') on conflict (email) do nothing;
insert into trip_members(trip_id,user_id,role) values
('00000000-0000-0000-0000-000000000010','00000000-0000-0000-0000-000000000001','OWNER'),
('00000000-0000-0000-0000-000000000010','00000000-0000-0000-0000-000000000002','EDITOR') on conflict (trip_id,user_id) do nothing;
