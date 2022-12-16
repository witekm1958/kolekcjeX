
-- Creating a user with ADMIN and USER roles.

INSERT INTO public.role (id_role, name) VALUES (1, 'ADMIN');
INSERT INTO public.role (id_role, name) VALUES (2, 'USER');
INSERT INTO public.profiles (user_profile, user_name, password, address_email, date_registration)
VALUES ('admin', 'admin', 'admin', 'admin@wp.pl', '2022-12-07 01:35:14.26');
INSERT INTO public.user_roles (id_user, id_role) VALUES (1, 1);
INSERT INTO public.user_roles (id_user, id_role) VALUES (1, 2);

























